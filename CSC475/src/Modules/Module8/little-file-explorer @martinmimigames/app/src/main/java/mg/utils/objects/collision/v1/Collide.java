package mg.utils.objects.collision.v1;

import mg.utils.math.Offset;

public class Collide {

  private static final int LEFT = 0;
  private static final int RIGHT = 1;
  private static final int TOP = 2;
  private static final int DOWN = 3;
  private final Offset offset;
  public Location location;
  public Size size;
  public float[] collisionEdges;
  private float collisionRadiusSquared;

  public Collide() {
    location = new Location();
    size = new Size();
    collisionEdges = new float[4];
    offset = new Offset();
  }

  //simplify number squaring with method
  private float sqr(float number) {
    return number * number;
  }

  public void setWidth(float width) {
    size.halfWidth = width / 2f;
  }

  public void setHeight(float height) {
    size.halfHeight = height / 2f;
  }

  public void updateRadius() {
    calculateRadius();
  }

  public void updateOffset() {
    if (location.angle == 0) calculateOffset();
    else calculateAngledOffset();
  }

  private void calculateOffset() {
    collisionEdges[LEFT] = location.x - size.halfWidth;
    collisionEdges[TOP] = location.y - size.halfHeight;
    collisionEdges[RIGHT] = location.x + size.halfWidth;
    collisionEdges[DOWN] = location.y + size.halfHeight;
  }

  private void calculateAngledOffset() {
    offset.setOffset(size.halfWidth * 2f, size.halfHeight * 2f);
    final float[][] points = new float[4][2];
    points[0][0] = location.x + offset.left.x(location.angle);
    points[0][1] = location.y + offset.left.y(location.angle);
    points[1][0] = location.x + offset.right.x(location.angle);
    points[1][1] = location.y + offset.right.y(location.angle);
    points[2][0] = location.x + offset.right.x(location.angle + 180);
    points[2][1] = location.y + offset.right.y(location.angle + 180);
    points[3][0] = location.x + offset.left.x(location.angle + 180);
    points[3][1] = location.y + offset.left.y(location.angle + 180);
    setCollisionEdge(points);
  }

  public void setCollisionEdge(float[][] collisionPoints) {
    float[] pointX = new float[collisionPoints.length];
    float[] pointY = new float[collisionPoints.length];
    for (int index = 0; index < collisionPoints.length; index++) {
      pointX[index] = collisionPoints[index][0];
      pointY[index] = collisionPoints[index][1];
    }
    float[] left_right = getSpecial(pointX);
    float[] up_down = getSpecial(pointY);
    collisionEdges[LEFT] = left_right[0];
    collisionEdges[RIGHT] = left_right[1];
    collisionEdges[TOP] = up_down[0];
    collisionEdges[DOWN] = up_down[1];
  }

  public boolean checkInCollisionRange(Collide target) {
    //uses pyth. theorem (A2 = B2 + C2) to calculate distance
    //between self and the target object.
    final float pointToPointDistance =
      sqr(this.location.x - target.location.x)
        + sqr(this.location.y - target.location.y);
    //distance between the center of self and
    //the center of the target object,
    //minus the collision radius of target and self
    //to start comparing when touched a possible side.
    final float actualDistance = pointToPointDistance
      - this.collisionRadiusSquared - target.collisionRadiusSquared;
    //no need to square root as square root of 0 or is still 0 or less.
    return (actualDistance <= 0);
  }

  public boolean checkInCollisionBounds(Collide target) {
    float[] self_sides = this.collisionEdges;
    float[] target_sides = target.collisionEdges;
    //comparing sides
    if (target_sides[0] < self_sides[0] && self_sides[0] < target_sides[1]) {
      if (self_sides[2] < target_sides[2] && self_sides[3] > target_sides[3]) return true;
      if (target_sides[2] < self_sides[2] && self_sides[2] < target_sides[3]) return true;
      if (target_sides[2] < self_sides[3] && self_sides[3] < target_sides[3]) return true;

    }
    if (target_sides[0] < self_sides[1] && self_sides[1] < target_sides[1]) {
      if (self_sides[2] < target_sides[2] && self_sides[3] > target_sides[3]) return true;
      if (target_sides[2] < self_sides[2] && self_sides[2] < target_sides[3]) return true;
      if (target_sides[2] < self_sides[3] && self_sides[3] < target_sides[3]) return true;

    }
    if (self_sides[0] < target_sides[0] && self_sides[1] > target_sides[1]) {
      if (self_sides[2] < target_sides[2] && self_sides[3] > target_sides[3]) return true;
      if (target_sides[2] < self_sides[2] && self_sides[2] < target_sides[3]) return true;
      return target_sides[2] < self_sides[3] && self_sides[3] < target_sides[3];
    }
    return false;
  }

  private float[] getSpecial(float[] arr) {

    float largest = Float.MIN_VALUE;
    float smallest = Float.MAX_VALUE;

    //find largest element of array
    for (float element : arr) {
      //check if largest is smaller than element
      if (largest < element) {
        //update largest
        largest = element;
      }
      //check if smallest is bigger than element
      if (smallest > element) {
        //update smallest
        smallest = element;
      }
    }
    return new float[]{smallest, largest};
  }

  private void calculateRadius() {
    collisionRadiusSquared = sqr(size.halfWidth) + sqr(size.halfHeight);
  }

  public static class Location {
    public float x;
    public float y;
    public float angle;
  }

  public static class Size {
    public float halfWidth;
    public float halfHeight;
  }

}

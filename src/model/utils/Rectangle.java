package model.utils;

import model.Entity;

/**
 * 
 * Class used for the collision system, creates a rectangle and checks for intersections between them.
 *
 */
public final class Rectangle {

    private Pair<Integer, Integer> position;
    private double width, height;

    /**
     * Rectangle builder.
     * 
     * @param pos       defines the initial position of the rectangle
     * @param width     defines the initial width of the rectangle
     * @param height    defines the initial height of the rectangle
     */
    public Rectangle(final Pair<Integer, Integer> pos, final double width, final double height) {
        this.position = pos;
        this.width = width;
        this.height = height;
    }

    /**
     * Rectangle empty builder. 
     * Used if you need to create the rectangle afterwards without initializing the parameters.
     */
    public Rectangle() {
        //empty
    }

    /**
     * Gets the position of the rectangle.
     * 
     * @return rectangle position
     */
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    /**
     * Sets the new, updated position of the rectangle.
     * 
     * @param pos defines the new position of the rectangle
     */
    public void setPosition(final Pair<Integer, Integer> pos) {
        this.position = pos;
    }

    /**
     * Gets the width of the rectangle.
     * 
     * @return rectangle width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Sets the new width of the rectangle.
     * 
     * @param width defines the new width of the rectangle
     */
    public void setWidth(final int width) {
        this.width = width;
    }

    /**
     * Gets the height of the rectangle.
     * 
     * @return rectangle height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Sets the new height of the rectangle.
     * 
     * @param height defines the new height of the rectangle
     */
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * Method to check if two rectangles collide up.
     * 
     * @param e defines the entity to check the collision
     * @return true if the rectangles collided up, false otherwise.
     */
    public boolean hasCollidedUp(final Entity e) { //WILDCARD
        double x1, x2, y1, xe1, xe2, ye1;

        x1 = this.getPosition().getX();
        x2 = this.getPosition().getX() + this.width;
        y1 = this.getPosition().getY();
        xe1 = e.getPosition().getX();
        xe2 = e.getPosition().getX() + this.width;
        ye1 = e.getPosition().getY() + this.height;

        if (x1 > xe1  || x1 < xe2) {
            if (y1 <= ye1) {
                return true;
            }
        } else if (x2 > xe1 || x2 < xe2) {
            if (y1 <= ye1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to check if two rectangles collide down.
     * 
     * @param rectangle is the rectangle you want to check if it collides with yours. 
     * @return true if the rectangles collided down, false otherwise.
     */
    public boolean hasCollidedDown(final Rectangle rectangle) {
        return true;
    }

    /**
     * Method to check if two rectangles collide left.
     * 
     * @param rectangle is the rectangle you want to check if it collides with yours. 
     * @return true if the rectangles collided left, false otherwise.
     */
    public boolean hasCollidedLeft(final Rectangle rectangle) {
        return true;
    }

    /**
     * Method to check if two rectangles collide right.
     * 
     * @param rectangle is the rectangle you want to check if it collides with yours. 
     * @return true if the rectangles collided right, false otherwise.
     */
    public boolean hasCollidedRight(final Rectangle rectangle) {
        return true;
    }

    /**
     * Method to check if two generic rectangles collide.
     * Used for generic collisions (like explosions) where you don't need to know the position (up, down, left, right) of the collision.
     * 
     * @param rectangle is the rectangle you want to check if it collides with yours. 
     * @return true if the rectangles collide, false otherwise.
     */
    public boolean intersectsWith(final Rectangle rectangle) {
        final double xr1 = this.position.getX();
        final double yr1 = this.position.getY();
        final double xr2 = rectangle.getPosition().getX();
        final double yr2 = rectangle.getPosition().getY();
        final double wr2 = rectangle.getWidth();
        final double hr2 = rectangle.getHeight();

        return xr1 + this.width >= xr2 &&       //if the first rectangle right edge goes past the second rectangle left edge 
                xr1 <= xr2 + wr2 &&             //if the first rectangle left edge goes past the second rectangle right edge
                yr1 + this.height >= yr2 &&     //if the first rectangle top edge goes past the second rectangle bottom edge
                yr1 <= yr2 + hr2;               //if the first rectangle bottom edge goes past the second rectangle top edge
    }
}

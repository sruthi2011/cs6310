/**
 * $Id$
 * $Date$
 */
package cs6310.proj1.data;

/**
 * @author Alex
 *
 */
public class EdgeTemperature {
	private float left;
	private float top;
	private float right;
	private float bottom;
	
	/**
	 * @param edge temperature values to be set
	 */
	EdgeTemperature(float left, float top, float right, float bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}
	/**
	 * @see default constructor
	 */
	public EdgeTemperature() {
		// TODO Auto-generated constructor stub
	}
	/**
     * @return the left
     */
    public float getLeft() {
    	return left;
    }
	/**
     * @param left the left to set
     */
    public void setLeft(float left) {
    	this.left = left;
    }
	/**
     * @return the top
     */
    public float getTop() {
    	return top;
    }
	/**
     * @param top the top to set
     */
    public void setTop(float top) {
    	this.top = top;
    }
	/**
     * @return the right
     */
    public float getRight() {
    	return right;
    }
	/**
     * @param right the right to set
     */
    public void setRight(float right) {
    	this.right = right;
    }
	/**
     * @return the bottom
     */
    public float getBottom() {
    	return bottom;
    }
	/**
     * @param bottom the bottom to set
     */
    public void setBottom(float bottom) {
    	this.bottom = bottom;
    }
}

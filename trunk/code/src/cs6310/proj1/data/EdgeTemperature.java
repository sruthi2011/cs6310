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
	public static final float DEFAULT_TEMP = (float) 50.0;
	private float left;
	private float top;
	private float right;
	private float bottom;
	
	/**
	 * @param edge temperature values to be set
	 */
	EdgeTemperature(float left, float top, float right, float bottom) {
		init(left, top, right, bottom);
	}
	/**
	 * @see default constructor
	 */
	public EdgeTemperature() {
		init(DEFAULT_TEMP, DEFAULT_TEMP, DEFAULT_TEMP, DEFAULT_TEMP);
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
    
    private void init(float left, float top, float right, float bottom) {
    	assert left >= 0.0 && left <= 100.0;
    	assert top >= 0.0 && top <= 100.0;
    	assert right >= 0.0 && right <= 100.0;
    	assert bottom >= 0.0 && bottom <= 100.0;
    	
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
    }
}

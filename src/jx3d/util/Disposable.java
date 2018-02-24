package jx3d.util;

/**
 * Disposable objects has memory that needs to be manually freed in order to avoid memory leaks.
 * @since 1.0
 * @author Aleman778
 */
public interface Disposable {

	/**
	 * Dispose methods is used to free memory and avoid memory leaks.
	 * This method should be called when the data is no longer needed or when program closes.
	 */
	public void dispose();
}

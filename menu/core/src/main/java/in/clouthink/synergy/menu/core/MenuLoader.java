package in.clouthink.synergy.menu.core;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @author dz
 */
public interface MenuLoader {

	/**
	 * load from input stream
	 *
	 * @param inputStream
	 * @return
	 */
	List<Menu> load(InputStream inputStream);

	/**
	 * load from file
	 *
	 * @param file
	 * @return
	 */
	List<Menu> load(File file);

}

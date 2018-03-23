package in.clouthink.synergy.menu.rest.view;

import in.clouthink.synergy.rbac.model.Resource;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
public class MenuView {

	private static void convert(Resource resource, MenuView target) {
		BeanUtils.copyProperties(resource, target);
	}

	public static MenuView from(Resource resource) {
		MenuView result = new MenuView();
		convert(resource, result);
		return result;
	}

	private boolean virtual;

	private String code;

	private String name;

	private String type;

	private Map<String,Object> metadata = new HashMap<>();

	private List<MenuView> children = new ArrayList<>();

	public boolean isVirtual() {
		return virtual;
	}

	public void setVirtual(boolean virtual) {
		this.virtual = virtual;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String,Object> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String,Object> metadata) {
		this.metadata = metadata;
	}

	public List<MenuView> getChildren() {
		return children;
	}

	public void setChildren(List<MenuView> children) {
		this.children = children;
	}
}

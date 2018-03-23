package in.clouthink.synergy.rbac.rest.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import in.clouthink.synergy.rbac.model.Resource;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * privileged resource tree
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrivilegedResourceWithChildrenView extends PrivilegedResourceView {

	public static PrivilegedResourceWithChildrenView from(Resource resource) {
		PrivilegedResourceWithChildrenView result = new PrivilegedResourceWithChildrenView();
		BeanUtils.copyProperties(resource, result, "actions");
		result.setActions(resource.getActions().stream().map(PrivilegedActionView::from).collect(Collectors.toList()));
		return result;
	}

	private List<PrivilegedResourceWithChildrenView> children = new ArrayList<>();

	public boolean hasChildren() {
		return children != null && !children.isEmpty();
	}

	@JsonDeserialize(contentAs = PrivilegedResourceWithChildrenView.class)
	public List<PrivilegedResourceWithChildrenView> getChildren() {
		return children;
	}

	public void setChildren(List<PrivilegedResourceWithChildrenView> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return super.toString() + "/DefaultResourceWithChildren{" +
			   "children=" + children +
			   '}';
	}

}

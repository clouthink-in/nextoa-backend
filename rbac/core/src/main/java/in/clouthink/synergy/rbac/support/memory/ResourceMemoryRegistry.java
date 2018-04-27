package in.clouthink.synergy.rbac.support.memory;

import in.clouthink.synergy.rbac.exception.ResourceException;
import in.clouthink.synergy.rbac.model.ParentAware;
import in.clouthink.synergy.rbac.model.Resource;
import in.clouthink.synergy.rbac.model.ResourceMatcher;
import in.clouthink.synergy.rbac.service.ResourceRegistry;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author dz
 */
public class ResourceMemoryRegistry implements ResourceRegistry {

    private boolean overrideEnabled = false;

    //The root resources (code)
    private List<String> rootResourceCodes = new ArrayList<>();

    // code => value
    private Map<String, Resource> resourceStore = new HashMap<>();

    // parent => children
    private Map<String, List<String>> parentChildrenMap = new HashMap<>();

    private String hashcode = UUID.randomUUID().toString();

    @Override
    public String getHashcode() {
        return hashcode;
    }

    void updateHashcode() {
        this.hashcode = UUID.randomUUID().toString();
    }

    @Override
    public void enableOverride() {
        this.overrideEnabled = true;
    }

    @Override
    public void disableOverride() {
        this.overrideEnabled = false;
    }

    @Override
    public void addResource(Resource resource) {
        String code = resource.getCode();
        Resource prevResource = resourceStore.get(code);
        if (prevResource != null) {
            if (!overrideEnabled) {
                throw new ResourceException(String.format(
                        "The value[code=%s] existed. And override is not allowed. Please invoke #enableOverride and try again.",
                        code));
            }
        }
        else {
            rootResourceCodes.add(code);
        }

        resourceStore.put(resource.getCode().trim(), resource);
        updateHashcode();
    }

    @Override
    public void addChildren(String parentCode, Resource... children) {
        if (StringUtils.isEmpty(parentCode)) {
            throw new ResourceException("The parent code should be not null or empty");
        }

        if (!resourceStore.containsKey(parentCode)) {
            throw new ResourceException("The parent is not found in the repository, please add it first.");
        }

        Stream.of(children).peek(child -> {
            //do validate
            Optional.ofNullable(resourceStore.get(child.getCode())).ifPresent(prevResource -> {
                if (!overrideEnabled) {
                    throw new ResourceException(String.format(
                            "The value[code=%s] existed. And override is not allowed. Please invoke #enableOverride and try again.",
                            child.getCode()));
                }
            });
        }).forEach(child -> {
            resourceStore.put(child.getCode(), child);
            addChildren(parentCode, child.getCode());
        });

        updateHashcode();
    }

    private void addChildren(String parentCode, String childCode) {
        List<String> childrenCodes = parentChildrenMap.get(parentCode);
        if (childrenCodes == null) {
            childrenCodes = new ArrayList<>();
            parentChildrenMap.put(parentCode, childrenCodes);
        }

        childrenCodes.add(childCode);

        updateHashcode();
    }

    @Override
    public Resource findByCode(String code) {
        return resourceStore.get(code);
    }

    @Override
    public List<Resource> getRootResources() {
        return rootResourceCodes.stream().map(code -> resourceStore.get(code)).collect(Collectors.toList());
    }

    @Override
    public List<Resource> getFlattenResources() {
        return resourceStore.values().stream().collect(Collectors.toList());
//        List<String> resourceCodes = new ArrayList<>();
//        resourceCodes.addAll(rootResourceCodes);
//        parentChildrenMap.values().stream().forEach(codes -> resourceCodes.addAll(codes));
//
//        return resourceCodes.stream().map(code -> resourceStore.get(code)).collect(Collectors.toList());
    }

    @Override
    public Resource getResourceParent(String resourceCode) {
        return Optional.ofNullable(resourceCode)
                       .map(code -> resourceStore.get(code))
                       .filter(resource -> resource instanceof ParentAware)
                       .map(resource -> resourceStore.get(((ParentAware) resource).getParentCode()))
                       .orElse(null);
    }

    @Override
    public List<Resource> getResourceChildren(String parentCode) {
        return Optional.ofNullable(parentCode)
                       .map(code -> parentChildrenMap.get(code))
                       .map(list -> list.stream()
                                        .map(code -> resourceStore.get(code))
                                        .collect(Collectors.toList()))
                       .orElse(Collections.emptyList());
    }

    @Override
    public Resource getFirstMatchedResource(ResourceMatcher resourceMatcher) {
        return doMatchFirstResource(resourceMatcher, this.rootResourceCodes);
    }

    private Resource doMatchFirstResource(ResourceMatcher resourceMatcher,
                                          List<String> codes) {
        for (String code : codes) {
            Resource resource = resourceStore.get(code);
            if (resourceMatcher.matched(resource)) {
                return resource;
            }

            List<String> childrenCodes = parentChildrenMap.get(code);
            if (childrenCodes != null) {
                Resource matchedChild = doMatchFirstResource(resourceMatcher, childrenCodes);
                if (matchedChild != null) {
                    return matchedChild;
                }
            }
        }

        return null;
    }

    @Override
    public List<Resource> getMatchedResources(ResourceMatcher resourceMatcher) {
        return this.resourceStore.values()
                                 .stream()
                                 .filter(resource ->
                                                 resourceMatcher.matched(resource))
                                 .collect(Collectors.toList());
    }

}

package in.clouthink.synergy.rbac.rest.service.impl;

import in.clouthink.synergy.rbac.rest.service.ResourceCacheService;
import in.clouthink.synergy.rbac.rest.view.ResourceDetailView;
import in.clouthink.synergy.rbac.rest.view.ResourceTreeView;
import in.clouthink.synergy.rbac.rest.view.ResourceView;
import in.clouthink.synergy.rbac.service.ResourceDiscovery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author dz
 */
@Service
public class ResourceCacheServiceMemoryImpl implements ResourceCacheService {

    public static final Object LOCK_OBJECT = new Object();

    @Autowired
    private ResourceDiscovery resourceDiscovery;

    private String cacheTreeHash = UUID.randomUUID().toString();

    private String cacheListHash = UUID.randomUUID().toString();

    private List<ResourceTreeView> cachedTree;

    private List<ResourceView> cachedList;

    @Override
    public List<ResourceTreeView> listHierarchyResources() {
        //try get cache
        if (!isTreeDirty()) {
            return cachedTree;
        }

        synchronized (LOCK_OBJECT) {
            //try again
            if (!isTreeDirty()) {
                return cachedTree;
            }

            //else build new one
            List<ResourceTreeView> result = resourceDiscovery.getRootResources()
                                                             .stream()
                                                             .map(ResourceTreeView::from)
                                                             .collect(Collectors.toList());

            processChildren(result);

            //cache it
            cachedTree = result;
            cacheTreeHash = resourceDiscovery.getHashcode();

            return result;
        }
    }

    @Override
    public List<ResourceTreeView> listHierarchyResources(boolean cached) {
        if (cached) {
            return listHierarchyResources();
        }

        List<ResourceTreeView> result = resourceDiscovery.getRootResources()
                                                         .stream()
                                                         .map(ResourceTreeView::from)
                                                         .collect(Collectors.toList());

        processChildren(result);

        return result;
    }

    @Override
    public List<ResourceView> listFlattenResources() {
        //try get cache
        if (!isListDirty()) {
            return cachedList;
        }

        synchronized (LOCK_OBJECT) {
            //try again
            if (!isListDirty()) {
                return cachedList;
            }
            //else build new one
            List<ResourceView> result = resourceDiscovery.getFlattenResources()
                                                         .stream()
                                                         .map(ResourceView::from)
                                                         .collect(Collectors.toList());

            //cache it
            cachedList = result;
            cacheListHash = resourceDiscovery.getHashcode();

            return result;
        }
    }

    @Override
    public List<ResourceView> listFlattenResources(boolean cached) {
        if (cached) {
            return listFlattenResources();
        }

        return resourceDiscovery.getFlattenResources()
                                .stream()
                                .map(ResourceView::from)
                                .collect(Collectors.toList());
    }

    @Override
    public ResourceDetailView getResourceDetail(String code) {
        return ResourceDetailView.from(resourceDiscovery.findByCode(code));
    }

    //*******************************************************
    // private
    //*******************************************************

    private boolean isTreeDirty() {
        return !resourceDiscovery.getHashcode().equals(cacheTreeHash);
    }

    private boolean isListDirty() {
        return !resourceDiscovery.getHashcode().equals(cacheListHash);
    }

    private void processChildren(List<ResourceTreeView> result) {
        result.stream().forEach(resource -> {
            List<ResourceTreeView> children = resourceDiscovery.getResourceChildren(resource.getCode())
                                                               .stream()
                                                               .map(ResourceTreeView::from)
                                                               .collect(Collectors.toList());
            resource.getChildren().addAll(children);
            processChildren(children);
        });
    }

}

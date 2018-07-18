package in.clouthink.synergy.test.common;

import in.clouthink.synergy.shared.domain.model.StringIdentifier;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.function.Consumer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class SimpleCrudControllerTest extends AbstractTest {

    protected HttpHeaders headers = new HttpHeaders();

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testCrud() throws Exception {
        try {
            this.testCreate();
            this.testList();
            this.testGetDetail();
            this.testNotFound();
            this.testUpdate();
        } finally {
            //clean up
            this.testDelete();
        }
    }

    protected void testDelete() throws Exception {

    }

    protected void testNotFound() throws Exception {

    }

    protected void testGetDetail() throws Exception {

    }

    protected void testList() throws Exception {

    }

    protected void testCreate() throws Exception {

    }

    protected void testUpdate() throws Exception {

    }

    protected <T> String doCreateEntityTest(String url,
                                            Object t,
                                            Class<T> clazz)
            throws Exception {
        return doCreateEntityTest(url, t, clazz, null);
    }

    protected <T> String doCreateEntityTest(String url,
                                            Object t,
                                            Class<T> clazz,
                                            Consumer<T> consumer)
            throws Exception {
        final String inputJson = TestUtils.mapToJson(t);

        final MvcResult result = mvc
                .perform(post(url)
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .accept(MediaType.APPLICATION_JSON)
                                 .content(inputJson)
                                 .headers(this.headers))
                .andExpect(status().isCreated())
                .andReturn();

        final String content = result.getResponse().getContentAsString();

        T created = TestUtils.mapFromJson(content, clazz);

        Assert.assertNotNull("failure - expected entity not null", created);
        if (created instanceof StringIdentifier) {
            Assert.assertNotNull("failure - expected entity#id not null", ((StringIdentifier) created).getId());
        }

        if (consumer != null) {
            consumer.accept(created);
        }

        return created instanceof StringIdentifier ? ((StringIdentifier) created).getId() : null;
    }

    protected <T> String doUpdateEntityTest(String url, String id, Object t, Class<T> clazz)
            throws Exception {
        return doUpdateEntityTest(url, id, t, clazz, null);
    }

    protected <T> String doUpdateEntityTest(String url, String id, Object t, Class<T> clazz,
                                            Consumer<T> consumer)
            throws Exception {
        final String inputJson = TestUtils.mapToJson(t);

        final MvcResult result = mvc
                .perform(put(url, id)
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .accept(MediaType.APPLICATION_JSON)
                                 .content(inputJson)
                                 .headers(this.headers))
                .andExpect(status().isOk())
                .andReturn();

        final String content = result.getResponse().getContentAsString();

        T updated = TestUtils.mapFromJson(content, clazz);

        Assert.assertNotNull("failure - expected entity not null", updated);
        if (updated instanceof StringIdentifier) {
            Assert.assertNotNull("failure - expected entity#id not null", ((StringIdentifier) updated).getId());
        }

        if (consumer != null) {
            consumer.accept(updated);
        }

        return updated instanceof StringIdentifier ? ((StringIdentifier) updated).getId() : null;
    }

    protected <T> void doGetEntityPageTest(String url, Class<T> clazz) throws Exception {
        doGetEntityPageTest(url, clazz, null);
    }

    protected <T> void doGetEntityPageTest(String url, Class<T> clazz,
                                           Consumer<Page<T>> consumer)
            throws Exception {
        final MvcResult result = mvc.perform(get(url)
                                                     .accept(MediaType.APPLICATION_JSON)
                                                     .headers(this.headers))
                                    .andExpect(status().isOk())
                                    .andReturn();

        final String content = result.getResponse().getContentAsString();

        Page<T> views = TestUtils.mapPageFromJson(content, clazz);
        Assert.assertNotNull(views);

        if (consumer != null) {
            consumer.accept(views);
        }
    }

    protected <T> void doGetEntityPageTest(String url, String var, Class<T> clazz)
            throws Exception {
        doGetEntityPageTest(url, var, clazz, null);
    }

    protected <T> void doGetEntityPageTest(String url,
                                           String var,
                                           Class<T> clazz,
                                           Consumer<Page<T>> consumer)
            throws Exception {
        final MvcResult result = mvc.perform(get(url, var)
                                                     .accept(MediaType.APPLICATION_JSON)
                                                     .headers(this.headers))
                                    .andExpect(status().isOk())
                                    .andReturn();

        final String content = result.getResponse().getContentAsString();
        Page<T> views = TestUtils.mapPageFromJson(content, clazz);
        Assert.assertNotNull(views);

        if (consumer != null) {
            consumer.accept(views);
        }
    }

    protected <T> void doGetEntityListTest(String url,
                                           Class<T> clazz) throws Exception {
        doGetEntityListTest(url, clazz, null);
    }

    protected <T> void doGetEntityListTest(String url,
                                           Class<T> clazz,
                                           Consumer<List<T>> consumer) throws Exception {
        final MvcResult result = mvc.perform(get(url)
                                                     .accept(MediaType.APPLICATION_JSON)
                                                     .headers(this.headers))
                                    .andExpect(status().isOk())
                                    .andReturn();

        final String content = result.getResponse().getContentAsString();
        List<T> views = TestUtils.mapListFromJson(content, clazz);
        Assert.assertNotNull(views);

        if (consumer != null) {
            consumer.accept(views);
        }
    }

    protected <T> void doGetEntityListTest(String url,
                                           String var,
                                           Class<T> clazz) throws Exception {
        doGetEntityListTest(url, var, clazz, null);
    }

    protected <T> void doGetEntityListTest(String url,
                                           String var,
                                           Class<T> clazz,
                                           Consumer<List<T>> consumer) throws Exception {
        final MvcResult result = mvc.perform(get(url, var)
                                                     .accept(MediaType.APPLICATION_JSON)
                                                     .headers(this.headers))
                                    .andExpect(status().isOk())
                                    .andReturn();

        final String content = result.getResponse().getContentAsString();
        List<T> views = TestUtils.mapListFromJson(content, clazz);
        Assert.assertNotNull(views);

        if (consumer != null) {
            consumer.accept(views);
        }
    }

    protected <T> void doGetEntityDetailTest(String url, String id, Class<T> clazz)
            throws Exception {
        doGetEntityDetailTest(url, id, clazz, null);
    }

    protected <T> void doGetEntityDetailTest(String url,
                                             String id,
                                             Class<T> clazz,
                                             Consumer<T> consumer)
            throws Exception {
        final MvcResult result = mvc.perform(get(url, id)
                                                     .accept(MediaType.APPLICATION_JSON)
                                                     .headers(this.headers))
                                    .andExpect(status().isOk())
                                    .andReturn();

        final String content = result.getResponse().getContentAsString();
        T view = TestUtils.mapFromJson(content, clazz);

        Assert.assertNotNull("failure - expected entity not null", view);
        if (view instanceof StringIdentifier) {
            Assert.assertNotNull("failure - expected entity#id not null", ((StringIdentifier) view).getId());
        }

        if (consumer != null) {
            consumer.accept(view);
        }
    }

    protected void doEntityNotFoundTest(String url, String id)
            throws Exception {
        mvc.perform(get(url, id)
                            .accept(MediaType.APPLICATION_JSON)
                            .headers(this.headers))
           .andExpect(status().isNotFound())
           .andReturn();
    }

    protected void doDeleteEntityTest(String url, String id)
            throws Exception {
        if (id == null) {
            return;
        }
        mvc.perform(delete(url, id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .headers(this.headers))
           .andExpect(status().isNoContent())
           .andReturn();
    }

}

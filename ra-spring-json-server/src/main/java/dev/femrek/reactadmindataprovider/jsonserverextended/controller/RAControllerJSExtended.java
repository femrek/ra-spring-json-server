package dev.femrek.reactadmindataprovider.jsonserverextended.controller;

import dev.femrek.reactadmindataprovider.jsonserver.controller.RAControllerJS;
import dev.femrek.reactadmindataprovider.jsonserverextended.service.IRAServiceJSExtended;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class RAControllerJSExtended<T, ID>
        extends RAControllerJS<T, ID>
        implements IRAControllerJSExtended<T, ID> {
    @Override
    protected abstract IRAServiceJSExtended<T, ID> getService();

    @Override
    public ResponseEntity<List<ID>> updateMany(List<ID> id, Map<String, Object> fields) {
        List<ID> ids = id != null ? id : Collections.emptyList();
        List<ID> updatedIds = getService().updateMany(ids, fields);
        return ResponseEntity.ok(updatedIds);
    }

    @Override
    public ResponseEntity<List<ID>> deleteMany(List<ID> id) {
        List<ID> ids = id != null ? id : Collections.emptyList();
        List<ID> deletedIds = getService().deleteMany(ids);
        return ResponseEntity.ok(deletedIds);
    }
}

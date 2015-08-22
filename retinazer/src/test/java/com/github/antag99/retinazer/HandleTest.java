package com.github.antag99.retinazer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class HandleTest {
    @Test
    public void testCreate() {
        Engine engine = new Engine(new EngineConfig());
        Handle entity = engine.createEntity();
        entity.create(FlagComponentA.class);
        assertNotNull(engine.getMapper(FlagComponentA.class).get(entity.getEntity()));
        engine.update();
        assertNotNull(engine.getMapper(FlagComponentA.class).get(entity.getEntity()));
    }

    @Test
    public void testAdd() {
        Engine engine = new Engine(new EngineConfig());
        Handle entity = engine.createEntity();
        entity.add(new FlagComponentA());
        assertNotNull(engine.getMapper(FlagComponentA.class).get(entity.getEntity()));
        engine.update();
        assertNotNull(engine.getMapper(FlagComponentA.class).get(entity.getEntity()));
    }

    @Test
    public void testRemove() {
        Engine engine = new Engine(new EngineConfig());
        Handle entity = engine.createEntity();
        entity.add(new FlagComponentA());
        entity.remove(FlagComponentA.class);
        assertNotNull(engine.getMapper(FlagComponentA.class).get(entity.getEntity()));
        engine.update();
        assertNull(engine.getMapper(FlagComponentA.class).get(entity.getEntity()));
        engine.update();
        assertNull(engine.getMapper(FlagComponentA.class).get(entity.getEntity()));
    }

    @Test
    public void testDuplicate() {
        Engine engine = new Engine(new EngineConfig());
        Handle entity = engine.createEntity();
        Handle reference = entity.duplicate();
        assertEquals(entity.getEngine(), reference.getEngine());
        assertNotNull(reference.getEngine());
        assertEquals(0, entity.getEntity());
        assertEquals(0, reference.getEntity());
    }

    @Test
    public void testDestroy() {
        Engine engine = new Engine(new EngineConfig());
        Handle entity = engine.createEntity().duplicate();
        entity.destroy();
        engine.update();
        assertFalse(engine.getEntities().contains(entity.getEntity()));
    }

    @Test
    public void testComponentsDestroyed() {
        Engine engine = new Engine(new EngineConfig());
        Handle entity = engine.createEntity().duplicate();
        entity.create(FlagComponentA.class);
        entity.destroy();
        engine.update();
        engine.createEntity();
        assertNull(engine.getMapper(FlagComponentA.class).get(entity.getEntity()));
    }
}

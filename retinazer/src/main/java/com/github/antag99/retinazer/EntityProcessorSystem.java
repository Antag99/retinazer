/*******************************************************************************
 * Copyright (C) 2015 Anton Gustafsson
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package com.github.antag99.retinazer;

public abstract class EntityProcessorSystem extends EntitySystem {
    private @Wire Engine engine;
    private FamilyConfig family;
    private EntitySet entities;

    public EntityProcessorSystem(FamilyConfig family) {
        this.family = family;
    }

    @Override
    public void setEngine(Engine engine) {
        super.setEngine(engine);

        entities = engine.getEntitiesFor(family);
    }

    public EntitySet getEntities() {
        return entities;
    }

    public FamilyConfig getFamily() {
        return family;
    }

    @Override
    public final void update() {
        processEntities();
    }

    protected void processEntities() {
        for (Entity entity : getEntities().getEntities()) {
            process(entity);
        }
    }

    protected abstract void process(Entity entity);
}

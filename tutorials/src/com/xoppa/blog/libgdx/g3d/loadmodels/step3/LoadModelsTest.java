/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.xoppa.blog.libgdx.g3d.loadmodels.step3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.utils.Array;
import com.xoppa.blog.libgdx.g3d.Grid;

import static com.xoppa.blog.libgdx.Main.data;

/**
 * See: http://blog.xoppa.com/loading-models-using-libgdx/
 * @author Xoppa
 */
public class LoadModelsTest extends Grid {
    public PerspectiveCamera cam;
    public CameraInputController camController;
    public ModelBatch modelBatch;
    public AssetManager assets;
    public Array<ModelInstance> instances = new Array<ModelInstance>();
    public Environment environment;
    public boolean loading;
     
    @Override
    public void create () {
        super.create();
        modelBatch = new ModelBatch();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(7f, 7f, 7f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        camController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(camController);

        assets = new AssetManager();
        assets.load(data+"/ship.obj", Model.class);
        loading = true;
    }
 
    private void doneLoading() {
        Model ship = assets.get(data+"/ship.obj", Model.class);
        for (float x = -5f; x <= 5f; x += 2f) {
            for (float z = -5f; z <= 5f; z += 2f) {
                ModelInstance shipInstance = new ModelInstance(ship);
                shipInstance.transform.setToTranslation(x, 0, z);
                instances.add(shipInstance);
            }
        }
        loading = false;
    }

    @Override
    public void render () {
        if (loading && assets.update())
            doneLoading();
        camController.update();
         
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
 
        modelBatch.begin(cam);
        modelBatch.render(instances, environment);
        modelBatch.render(mGridInstance);
        modelBatch.render(mArrowInstance);
        modelBatch.end();
    }
     
    @Override
    public void dispose () {
        super.dispose();
        modelBatch.dispose();
        instances.clear();
        assets.dispose();
    }

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
package com.xoppa.blog.libgdx.g3d;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

/**
 * 类描述：
 *
 * @author caosc
 * @date 2019-11-12
 */
public class Grid implements ApplicationListener {
    private Model mGrid;
    protected ModelInstance mGridInstance;
    private Model mArrow;
    protected ModelInstance mArrowInstance;
    private Model mCoordinates;
    protected ModelInstance mCoordinatesInstance;
    @Override
    public void create() {
        ModelBuilder modelBuilder = new ModelBuilder();

        mGrid = modelBuilder.createLineGrid(30, 30, 1, 1,
                new Material(ColorAttribute.createDiffuse(Color.WHITE)), VertexAttributes.Usage.Position);
        mGridInstance = new ModelInstance(mGrid);
        mArrow = modelBuilder.createArrow(0, 0, 0, 0, 15, 0, 0.04f, 0.02f,
                100, GL20.GL_LINES, new Material(ColorAttribute.createDiffuse(Color.GREEN)), VertexAttributes.Usage.Position);
        mArrowInstance = new ModelInstance(mArrow);
        mCoordinates = modelBuilder.createXYZCoordinates(17, 0.05f, 0.01f, 100,
                GL20.GL_LINES, new Material(ColorAttribute.createDiffuse(Color.BLUE)), VertexAttributes.Usage.Position);
        mCoordinatesInstance = new ModelInstance(mCoordinates);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        mGrid.dispose();
        mArrow.dispose();
        mCoordinates.dispose();
    }
}

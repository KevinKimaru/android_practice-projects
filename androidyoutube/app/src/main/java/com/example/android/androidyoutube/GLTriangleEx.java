package com.example.android.androidyoutube;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Kevin Kimaru Chege on 7/10/2017.
 */

public class GLTriangleEx {

    private float vertices[] = {
            0f, 1f,
            1f, 1f,
            -1f, -1f
    };

    private float rgbaVals[] = {
            1, 0, 0, .5f,
            .5f, 1, .8f, .3f,
            .7f, .1f, 1, 1
    };

    private FloatBuffer mFloatBuffer, colorBuff;

    private short[] indices = {0, 1, 2};
    private ShortBuffer mShortBuffer;

    public GLTriangleEx() {
        ByteBuffer fBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
        fBuffer.order(ByteOrder.nativeOrder());
        mFloatBuffer = fBuffer.asFloatBuffer();
        mFloatBuffer.put(vertices);
        mFloatBuffer.position(0);

        ByteBuffer pBuffer = ByteBuffer.allocateDirect(indices.length * 2);
        pBuffer.order(ByteOrder.nativeOrder());
        mShortBuffer = pBuffer.asShortBuffer();
        mShortBuffer.put(indices);
        mShortBuffer.position(0);

        ByteBuffer cBuffer = ByteBuffer.allocateDirect(rgbaVals.length * 4);
        cBuffer.order(ByteOrder.nativeOrder());
        colorBuff = cBuffer.asFloatBuffer();
        colorBuff.put(rgbaVals);
        colorBuff.position(0);
    }

    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CW);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, mFloatBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuff);
        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, mShortBuffer);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }

}

package com.example.android.androidyoutube;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Kevin Kimaru Chege on 7/9/2017.
 */

public class GLCube {
    private float vertices[] = {
            1, 1, -1,//top Front Right
            1, -1, -1,//Bottom Front Right
            -1, -1, -1,//Bottom Front left
            -1, 1, -1,//top Front left

            1, 1, 1,//top Back Right
            1, -1, 1,//Bottom Back Right
            -1, -1, 1,//Bottom Back left
            -1, 1, 1//top Back left
    };
    private FloatBuffer mFloatBuffer;

    private short[] indices = {
            3,4,0,   0,4,1,   3,0,1,
            3,7,4,   7,6,4,   7,3,6,
            3,1,2,   1,6,2,   6,3,2,
            1,4,5,   5,6,1,   6,5,4
    };
    private ShortBuffer mShortBuffer;

    public GLCube() {
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
    }

    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mFloatBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, mShortBuffer);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);
    }
}

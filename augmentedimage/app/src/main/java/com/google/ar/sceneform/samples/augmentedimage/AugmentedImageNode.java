/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.ar.sceneform.samples.augmentedimage;

import android.content.Context;
import android.util.Log;

import com.google.ar.core.Anchor;
import com.google.ar.core.AugmentedImage;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ViewRenderable;


import java.util.concurrent.CompletableFuture;

/**
 * Node for rendering an augmented image. The image is framed by placing the virtual picture frame
 * at the corners of the augmented image trackable.
 */
@SuppressWarnings({"AndroidApiChecker"})
public class AugmentedImageNode extends AnchorNode {


    private Anchor anchor = null;
    private String Name;
    private  Context context;
    public AugmentedImageNode(Context context) {
        this.context=context;


    }

    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    public void setImage(AugmentedImage image,String Name) {
        this.Name=Name;
        Log.d("imageName",Name);
        // If any of the models are not loaded, then recurse when all are loaded.


        this.anchor = image.createAnchor(image.getCenterPose());
        // Set the anchor based on the center of the image.
        setAnchor(this.anchor);
        Log.d("ImageName",Name) ;
        if(Name.equals("earth.jpg"))
        {

        }


    }



    private void setViewRenderable(CompletableFuture<ViewRenderable> renderable,float x,float y,float z){
        Node cornerNode;
        Vector3 localPosition = new Vector3();
        localPosition.set(x,y,z);
        cornerNode = new Node();
        cornerNode.setParent(this);
        cornerNode.setLocalPosition(localPosition);
        cornerNode.setRenderable(renderable.getNow(null));
    }
    private void setVerticalRenderable(CompletableFuture<ViewRenderable> renderable,float x,float y,float z){
        Node cornerNode;
        Vector3 localPosition = new Vector3();
        localPosition.set(x,y,z);
        cornerNode = new Node();
        cornerNode.setParent(this);
        cornerNode.setLookDirection(Vector3.forward());
        cornerNode.setLocalPosition(localPosition);
        cornerNode.setRenderable(renderable.getNow(null));
    }
    private void setHorizontalRenderable(CompletableFuture<ViewRenderable> renderable,float x,float y,float z){
        Node cornerNode;
        Vector3 localPosition = new Vector3();
        localPosition.set(x,y,z);
        cornerNode = new Node();
        cornerNode.setParent(this);
        cornerNode.setLocalPosition(localPosition);
        //cornerNode.setLookDirection(Vector3.down(),Vector3.forward());
        cornerNode.setLocalRotation(Quaternion.axisAngle(new Vector3(1, 0, 0), -90f));
        cornerNode.setRenderable(renderable.getNow(null));
    }



}

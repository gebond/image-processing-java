package com.gebond.ip.math.func.image;

import com.gebond.ip.math.func.context.ImageContext;
import com.gebond.ip.math.func.operation.Operation;
import com.gebond.ip.math.func.operation.OperationManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 17/02/18.
 */
public class ImageProcessing extends OperationManager<ImageContext>{
    @Override
    public List<Operation<ImageContext>> getOperations() {
        return new ArrayList<>();
    }
}

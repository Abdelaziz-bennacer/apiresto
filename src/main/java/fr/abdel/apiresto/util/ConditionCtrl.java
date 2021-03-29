package fr.abdel.apiresto.util;

import fr.abdel.apiresto.exception.ResourceNotFoundException;

public  final class ConditionCtrl {

    public static <T> T checkFound(T object) {

        if(object == null) {
            throw  new ResourceNotFoundException();
        }
        return object;
    }
}

package org.kd.presentation;

import org.kd.logic.Board;
import org.kd.logic.IGraphicalOutput;
import org.kd.logic.Track;
import org.kd.logic.Vehicle;

import javax.swing.*;

/*
uses OpenGL
 */
public class Draw3d implements IGraphicalOutput {

    private static String NOT_SUPPORTED_EXCEPTION_MSG = "Not supported yet.";

    public Draw3d(JPanel drawablePanel) {
        throw new RuntimeException("3d not implemented yet");
    }

    @Override
    public void draw(Board board) {

        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION_MSG);
    }

    @Override
    public void draw(Track track) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION_MSG);
    }

    @Override
    public void draw(Vehicle vehicle) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION_MSG);
    }

    @Override
    public void setPanelToDrawOn(JPanel drawablePanel) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION_MSG);
    }

    @Override
    public void clearOutput() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION_MSG);
    }

    @Override
    public void eraseVehicles(Board board) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION_MSG);
    }

    @Override
    public void drawAllVehicles(Board board) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION_MSG);
    }

    @Override
    public void drawTrackBorder(Track track) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION_MSG);
    }

    @Override
    public void drawStartLine(Track track) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION_MSG);
    }

}

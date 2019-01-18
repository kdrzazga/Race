package org.kd.logic.creation;

import org.kd.logic.Board;
import org.kd.logic.Vehicle;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BoardBuilder {

    private Board board;

    public enum TrackType {
        RECTANGULAR, DONUT, KIDNEY, SINE, TRIANGLE, PENTAGON, WERONIKA, TEST_RECTANGULAR;

        @Override
        public String toString() {

            Map<TrackType, String> toStringMap = Map.of(
                    RECTANGULAR, "Rectangular track",
                    DONUT, "Donut",
                    KIDNEY, "Kidney",
                    SINE, "Sine",
                    TRIANGLE, "Triagle",
                    PENTAGON, "Pentagon track",
                    WERONIKA, "Weronika"
            );

            return Optional
                    .ofNullable(toStringMap.get(this))
                    .orElse("Test rectangular");
        }
    }

    public BoardBuilder createBoard() {
        this.board = new Board();
        return this;
    }

    public BoardBuilder withTrack(TrackType type) {
        this.board.track = new TrackFactory().createTrack(type);
        return this;
    }

    public BoardBuilder withVehicles(int numberOfVehicles) {
        this.board.vehicles = new VehicleFactory().createVehicles(numberOfVehicles, this.board);
        return this;
    }

    public BoardBuilder withVehicles(List<Vehicle> vehicles) {
        this.board.vehicles = vehicles;
        return this;
    }

    public Board build() {
        return this.board;
    }
}

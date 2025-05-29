import engine.MovementSimulator;
import engine.SimulationState;
import exceptions.IllegalOrientationException;
import exceptions.WrongFileFormatException;
import parser.FileParser;
import writter.GameStateWriter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws WrongFileFormatException, IllegalOrientationException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Should have two arguments. args[0] is input file, args[1] is output file");
        }
        final SimulationState simulationState = FileParser.parseFile(args[0]);
        final MovementSimulator movementSimulator = new MovementSimulator(simulationState);
        movementSimulator.simulate();
        GameStateWriter.writeFile(simulationState, args[1]);
    }
}
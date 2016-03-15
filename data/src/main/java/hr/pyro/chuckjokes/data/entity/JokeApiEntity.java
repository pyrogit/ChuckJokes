package hr.pyro.chuckjokes.data.entity;

/**
 * Created on 12.03.16..
 */
public final class JokeApiEntity {
    private String type;
    private Value value;

    public String getType() {
        return type;
    }

    public Value getValue() {
        return value;
    }

    public class Value {
        private int id;
        private String joke;

        public int getId() {
            return id;
        }

        public String getJoke() {
            return joke;
        }
    }
}

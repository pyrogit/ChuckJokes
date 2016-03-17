package hr.pyro.chuckjokes.domain.model;

/**
 * Created on 07.03.16..
 */
public final class JokeDomainModel {

    public static final JokeDomainModel EMPTY = new Builder().id(-1)
            .favorite(false)
            .joke("")
            .build();
    public final int id;
    public final String joke;
    public final boolean isFavorite;

    private JokeDomainModel(final Builder builder) {
        this.id = builder.id;
        this.joke = builder.joke;
        this.isFavorite = builder.isFavorite;
    }

    public static JokeDomainModel.Builder create() {
        return new JokeDomainModel.Builder();
    }

    public static final class Builder {

        private int id;
        private String joke;
        private boolean isFavorite;

        public Builder joke(String joke) {
            this.joke = joke;
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder favorite(boolean isFavorite) {
            this.isFavorite = isFavorite;
            return this;
        }

        public JokeDomainModel build() {
            return new JokeDomainModel(this);
        }
    }
}

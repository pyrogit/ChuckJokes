package hr.pyro.chuckjokes.presentation.util;

public interface ErrorMessageFactory {

    String createMessageForError(Throwable error);
}

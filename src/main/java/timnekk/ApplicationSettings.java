package timnekk;

import timnekk.input.CommandProviderFactory;
import timnekk.output.OutputWriter;
import timnekk.storage.BookStorage;

public record ApplicationSettings(
        CommandProviderFactory commandProviderFactory,
        BookStorage bookLibrary,
        BookStorage bookBundle,
        OutputWriter outputWriter
) {
}

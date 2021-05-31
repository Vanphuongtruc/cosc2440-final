package s3818074.filters;

import s3818074.models.Library;

import java.util.List;
import java.util.stream.Collectors;

public class LibraryFilter {
    private List<Library> libraries;

    public LibraryFilter(List<Library> libraries) {
        this.libraries = libraries;
    }

    public LibraryFilter withSubject(String subject) {
        if (subject == null) return this;
        libraries = libraries.stream().filter(l -> l.getSubject() != null && l.getSubject().equalsIgnoreCase(subject)).collect(Collectors.toList());
        return this;
    }

    public List<Library> get() {
        return libraries;
    }
}

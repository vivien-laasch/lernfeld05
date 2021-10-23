package lf05;

public enum SQL {
    example1 ("SQL anfrage hier als String"),
    Example2("...");

    private final String anfrage;

    private SQL(String anfrage)
    {
        this.anfrage = anfrage;
    }

    public String getString()
    {
        return this.anfrage;
    }
}

package tcc.job.devs.utils;

public interface LoggerService {

    /**
     * Registra uma mensagem de informação.
     *
     * @param message a mensagem a ser registrada
     */
    void info(String message);

    /**
     * Registra uma mensagem de erro.
     *
     * @param message a mensagem de erro a ser registrada
     */
    void error(String message);

    /**
     * Registra uma exceção.
     *
     * @param e a exceção a ser registrada
     */
    void error(Exception e);

    /**
     * Registra uma exceção com uma mensagem adicional.
     *
     * @param message a mensagem adicional a ser registrada
     * @param e       a exceção a ser registrada
     */
    void error(String message, Exception e);

    /**
     * Registra uma mensagem de aviso.
     *
     * @param message a mensagem de aviso a ser registrada
     */
    void warn(String message);

    // Você pode adicionar outros métodos conforme necessário,
    // como debug(), trace(), etc.
}


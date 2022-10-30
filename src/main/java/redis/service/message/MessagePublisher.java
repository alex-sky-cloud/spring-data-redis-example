package redis.service.message;

public interface MessagePublisher {

    void publish(final String message);
}

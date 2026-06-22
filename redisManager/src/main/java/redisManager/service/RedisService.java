package redisManager.service;

public interface RedisService {
	String set(String key,String value);
	String set(String key,long expiration,String value);
	long remove(String key);
	String get(String key);
	boolean exists(String key);
}

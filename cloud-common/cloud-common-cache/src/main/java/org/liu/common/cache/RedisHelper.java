package org.liu.common.cache;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.StaticScriptSource;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * redis操作帮助类
 * 因要注入jacksonRedisTemplate，需交给spring做bean管理
 * 跟util工具区分开来
 */
@AllArgsConstructor
public class RedisHelper {

	private StringRedisTemplate stringRedisTemplate;
	private RedisTemplate<String, Object> jacksonRedisTemplate;

	/**
	 * 获取锁
	 *
	 * @param key
	 * @param requestId
	 * @param expireTime
	 * @return
	 */
	public boolean tryLock(String key, String requestId, Duration expireTime) {
		return stringRedisTemplate.boundValueOps(key).setIfAbsent(requestId, expireTime);
	}

	/**
	 * 释放锁
	 *
	 * @param key
	 * @param requestId
	 */
	public boolean releaseLock(String key, String requestId) {
		String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
		DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
		redisScript.setResultType(Long.class);
		redisScript.setScriptSource(new StaticScriptSource(script));
		List<String> keys = Collections.singletonList(key);
		Long result = stringRedisTemplate.execute(redisScript, keys, requestId);
		if (null == result || 0 == result) {
			return false;
		}
		return true;
	}

	/**
	 * 将long类型的id转换为byte，要先转为string，再获取string的bytes
	 *
	 * @param id
	 * @return
	 */
	private byte[] getIdField(Long id) {
		return id.toString().getBytes();
	}

	/**
	 * 保存手机验证码
	 *
	 * @param mobile
	 * @return
	 */
	public Boolean saveCode(String mobile, String code) {
		return stringRedisTemplate.boundValueOps(RedisCode.CODE + mobile).setIfAbsent(code, RedisCode.CODE_EXPIRE_TIME);
	}

	/**
	 * 获取手机验证码
	 *
	 * @param mobile
	 * @return
	 */
	public String getCode(String mobile) {
		String code = stringRedisTemplate.boundValueOps(RedisCode.CODE + mobile).get();
		return code;
	}

	/**
	 * 删除手机验证码
	 *
	 * @param mobile
	 */
	public void deleteCode(String mobile) {
		stringRedisTemplate.delete(RedisCode.CODE + mobile);
	}

	/**
	 * 将会话保存至redis
	 *
	 * @param token
	 * @param value
	 */
	public void saveToken(String token, Object value) {
		jacksonRedisTemplate.opsForValue().set(RedisCode.TOKEN + token, value, RedisCode.TOKEN_EXPIRE_TIME);
	}

	/**
	 * 删除会话
	 *
	 * @param token
	 */
	public void deleteToken(String token) {
		jacksonRedisTemplate.delete(RedisCode.TOKEN + token);
	}

	/**
	 * 根据token获取会话
	 *
	 * @param token
	 * @return
	 */
	public Object getToken(String token) {
		return jacksonRedisTemplate.opsForValue().get(RedisCode.TOKEN + token);
	}

	/**
	 * 设置token的失效时长
	 *
	 * @param token
	 */
	public void expireToken(String token) {
		jacksonRedisTemplate.boundValueOps(RedisCode.TOKEN + token).expire(RedisCode.TOKEN_EXPIRE_TIME.getSeconds(), TimeUnit.SECONDS);
	}

	public void saveCaptcha(String username, String capText) {
		jacksonRedisTemplate.boundValueOps(RedisCode.CAPTCHA + username).set(capText);
	}

	public String getCaptcha(String username) {
		return (String) jacksonRedisTemplate.boundValueOps(RedisCode.CAPTCHA + username).get();
	}

	public void deleteCaptcha(String username) {
		jacksonRedisTemplate.delete(RedisCode.CAPTCHA + username);
	}

	public String queryTodayQrCode(String ymd){
		String s = stringRedisTemplate.boundValueOps(RedisCode.TODAY_QRCODE_CODE + ymd).get();
		if (null == s) {
			return "0";
		}
		return s;
	}

	public String getTodayQrcodeCode(String ymd) {
		return getTodayCode(RedisCode.TODAY_QRCODE_CODE, ymd);
	}

	private String getTodayCode(String codePrefix, String ymd){
		//KEY[1]--RedisCode.TODAY_SPU_CODE + ymd
		//ARGV[1]--RedisCode.TODAY_SPU_CODE_EXPIRE_TIME
		String script = "if redis.call('exists', KEYS[1]) == 0 then redis.call('set', KEYS[1], 1, 'EX', ARGV[1]); return '1'; " +
				"else local userCode = redis.call('get', KEYS[1]); redis.call('set', KEYS[1], userCode + 1); return tostring(userCode + 1); " +
				"end";
		DefaultRedisScript<String> redisScript = new DefaultRedisScript<>();
		redisScript.setResultType(String.class);
		redisScript.setScriptSource(new StaticScriptSource(script));
		List<String> keys = Collections.singletonList(codePrefix + ymd);
		String result = stringRedisTemplate.execute(redisScript, keys, RedisCode.TODAY_CODE_EXPIRE_TIME + "");
		return result;
	}
}

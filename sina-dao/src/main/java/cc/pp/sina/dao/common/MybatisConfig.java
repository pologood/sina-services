package cc.pp.sina.dao.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConfig {

	public enum ServerEnum {
		local, fenxi, tongling, pp, read, write, friend1, friend2, other
	}

	private static Map<ServerEnum, SqlSessionFactory> sessionFactorys = new HashMap<>();

	static {
		for (ServerEnum server : ServerEnum.values()) {
			try (InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");) {
				sessionFactorys.put(server, new SqlSessionFactoryBuilder().build(inputStream, server.name()));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static SqlSessionFactory getSqlSessionFactory(ServerEnum server) {
		return sessionFactorys.get(server);
	}

}

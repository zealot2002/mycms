package org.zzy.mycms.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.zzy.mycms.bean.Category;
import org.zzy.mycms.bean.Channel;
import org.zzy.mycms.bean.Menu;
import org.zzy.mycms.bean.Model;
import org.zzy.mycms.bean.Module;
import org.zzy.mycms.bean.Permission;
import org.zzy.mycms.bean.Plugin;
import org.zzy.mycms.bean.User;
import org.zzy.mycms.bean.UserGroup;

/**
 *  数据库查询结果集映射对象处理
 * 
 * 注意：这样做的目的是为了简化Dao实现的代码。而static final，这个是Spring3.2官方文档推荐使用的。
 * 
 * @author marker
 */
public final class ObjectRowMapper {

	// 栏目RowMapper
	public static final class RowMapperChannel implements RowMapper<Channel> { 
		public Channel mapRow(ResultSet rs, int arg1) throws SQLException {
			Channel channel = new Channel();
			channel.setId(rs.getInt("id"));
			channel.setPid(rs.getLong("pid"));
			channel.setName(rs.getString("name"));
			channel.setTemplate(rs.getString("template")); 
			channel.setUrl( rs.getString("url"));// URL地址
			channel.setRows(rs.getInt("rows"));// 分页条数目
			channel.setIcon(rs.getString("icon"));// 图标
			channel.setKeywords(rs.getString("keywords"));
			channel.setDescription(rs.getString("description"));
			channel.setRedirect(rs.getString("redirect"));// 重定向地址
			channel.setHide(rs.getShort("hide"));
			channel.setLangkey(rs.getString("langkey"));// 国际化
			return channel;
		}
	}
	
	
	// 菜单RowMapper
	public static final class RowMapperMenu implements RowMapper<Menu> {
		public Menu mapRow(ResultSet rs, int arg1) throws SQLException {
			Menu menu = new Menu();
			menu.setId(rs.getInt("id"));
			menu.setPid(rs.getInt("pid"));
			menu.setName(rs.getString("name"));
			menu.setIcon(rs.getString("icon"));
			menu.setSort(rs.getInt("sort"));
			menu.setUrl(rs.getString("url"));
			return menu;
		}
	}
	
	
	// 用户 RowMapper
	public static final class RowMapperUser implements RowMapper<User> {
		public User mapRow(ResultSet rs, int num) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setNickname(rs.getString("nickname"));
			user.setLogintime(rs.getDate("logintime"));
			user.setGid(rs.getInt("gid"));// 分组ID
			user.setStatus(rs.getShort("status"));
			return user;
		}
	}
	
	
	// 插件 RowMapper
	public static final class RowMapperPlugin implements RowMapper<Plugin> {
		public Plugin mapRow(ResultSet rs, int num) throws SQLException {
			Plugin plugin = new Plugin();
			plugin.setId(rs.getInt("id"));
			plugin.setName(rs.getString("name"));
			plugin.setUri(rs.getString("uri"));
			plugin.setMark(rs.getString("mark"));
			plugin.setStatus(rs.getInt("status"));
			plugin.setDescription(rs.getString("description"));
			return plugin;
		}
	}
	
	
	// 内容模型 RowMapper
	public static final class RowMapperModule implements RowMapper<Module> {
		public Module mapRow(ResultSet rs, int num) throws SQLException {
			Module module = new Module();
			module.setId(rs.getLong("id"));
			module.setName(rs.getString("name"));
			module.setUri(rs.getString("uri"));
			module.setType(rs.getString("type"));
			module.setTemplate(rs.getString("template"));
			module.setVersion(rs.getInt("version"));
			module.setModule(rs.getString("module"));
			return module;
		}
	}
	
	// 权限 RowMapper
	public static final class RowMapperPermission implements RowMapper<Permission>{
		public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {
			Permission permission = new Permission();
			permission.setGid(rs.getInt("gid"));
			permission.setMid(rs.getInt("mid"));
			return permission;
		}
		
	}
	
	// 用户分组 RowMapper
	public static final class RowMapperUserGroup implements RowMapper<UserGroup>{
		public UserGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserGroup group = new UserGroup();
			group.setId(rs.getInt("id"));
			group.setName(rs.getString("name"));
			group.setScope(rs.getInt("scope"));
			group.setDescription(rs.getString("description"));
			return group;
		}
		
	}
	
	
	// 内容模型rowmapper
	public static final class RowMapperModel implements RowMapper<Model>{

		public Model mapRow(ResultSet rs, int arg1) throws SQLException {
			Model model = new Model();
			
			model.setId(rs.getInt("id"));
			model.setName(rs.getString("name"));
			model.setIcon(rs.getString("icon"));
			model.setVersion(rs.getString("version"));
			model.setTemplate(rs.getString("template"));
			model.setType(rs.getString("type"));
			model.setAuthor(rs.getString("author")); 
			model.setTime(rs.getTimestamp("time"));
			model.setModule(rs.getString("module"));
			
			return model;
		}
		
	}
	
	
	
	
	// 分类(Category)
	public static final class RowMapperCategory implements RowMapper<Category>{

		public Category mapRow(ResultSet rs, int arg1) throws SQLException {
			Category categroy = new Category();
			categroy.setId(rs.getInt("id"));
			categroy.setName(rs.getString("name"));
			categroy.setPid(rs.getInt("pid"));
			categroy.setRoot(rs.getInt("root"));
			categroy.setAlias(rs.getString("alias"));
			categroy.setSort(rs.getInt("sort"));
			categroy.setDescription(rs.getString("description"));
			categroy.setType(rs.getString("type"));
			categroy.setModel(rs.getString("model"));
			return categroy;
		}
		
	}
}

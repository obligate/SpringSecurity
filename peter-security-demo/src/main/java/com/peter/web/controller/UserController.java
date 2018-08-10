/**
 * 
 */
package com.peter.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.peter.dto.User;
import com.peter.dto.UserQueryCondition;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @RestController   		表明此controller 提供RestAPI
 * @RequestMapping及变体   	映射http请求url到java方法
 * @RequestParam     		映射请求参数到java方法的参数
 * @PageableDefault  		指定分页参数默认值
 * @author peter
 *
 */
@RestController 
@RequestMapping("/user")
public class UserController {
	
//	@GetMapping("/me")
//	public Object getCurrentUser() {
//		return SecurityContextHolder.getContext().getAuthentication();
//	}
	
//	/**
//	 * 传入Authentication,spring默认会从SecurityContextHolder找,相对于上面可以简化代码
//	 * @param authentication
//	 * @return
//	 */
//	@GetMapping("/me")
//	public Object getCurrentUser(Authentication authentication) {
//		return authentication;
//	}
	
	/**
	 * 只返回用户相关的信息
	 * @param userDetails
	 * @return
	 */
	@GetMapping("/me")
	public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
		return userDetails;
	}
	
	@PostMapping
	public User createUser(@Valid @RequestBody User user, BindingResult errors) {
		
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		
		user.setId("1");
		return user;
	}
	@PutMapping("/{id:\\d+}")
	public User updateUser(@Valid @RequestBody User user, BindingResult errors) {
		
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> {
//				FieldError fieldError = (FieldError)error;
//				String message = fieldError.getField() + " "+ error.getDefaultMessage();
				System.out.println(error.getDefaultMessage());
				});
		}
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		
		user.setId("1");
		return user;
	}
	
	@GetMapping
//	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@JsonView(User.UserSimpleView.class)
	@ApiOperation(value="用户查询服务")
	public List<User> query(UserQueryCondition condition, @PageableDefault(page=2,size=17,sort="username,asc") Pageable pageable
			/*@RequestParam(name="username",required=false,defaultValue="jack") String name*/){
		System.out.println(ReflectionToStringBuilder.toString(condition,ToStringStyle.MULTI_LINE_STYLE));
		System.out.println(pageable.getPageNumber());
		System.out.println(pageable.getPageSize());
		System.out.println(pageable.getSort());
		List<User> retList = new ArrayList<>();
		retList.add(new User());
		retList.add(new User());
		retList.add(new User());
		return retList;
	}
	@GetMapping("/{id:\\d+}")
//	@RequestMapping(value="/user/{id:\\d+}", method = RequestMethod.GET)
	@JsonView(User.UserDetailView.class)
	public User getInfo(@ApiParam(value="用户id") @PathVariable(name = "id") String idx) {
		// 测试默认异常和自定义异常的情况
//		throw new UserNotExistException(idx);
		
		//测试正常请求情况
		System.out.println("进入getInfo服务");
		User  user = new User();
		user.setUsername("tom");
		return user;
	}
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable  String id) {
		System.out.println(id);
	}
}

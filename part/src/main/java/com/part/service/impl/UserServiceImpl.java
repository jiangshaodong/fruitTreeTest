package com.part.service.impl;

import com.part.entity.User;
import com.part.dao.UserDao;
import com.part.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiangSD
 * @since 2019-10-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
	
}

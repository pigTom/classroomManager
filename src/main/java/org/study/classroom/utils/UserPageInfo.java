package org.study.classroom.utils;

import org.study.classroom.model.ClassroomUser;

import java.util.Iterator;
import java.util.List;

public class UserPageInfo extends AbstractPageInfo<ClassroomUser>{
	public UserPageInfo(List<ClassroomUser> users) {
		super(users);
	}
	public void deleteById(Long id){
		if (id == null) {
			return;
		}
		List<ClassroomUser> data = this.getData();
		if( data==null || data.size()==0)
			return;
		Iterator<ClassroomUser> iterator = data.iterator();
		while(iterator.hasNext()){
			ClassroomUser user = iterator.next();
			if( user.getId() == id)
				iterator.remove();
		}
	}

	public void updateById(ClassroomUser user) {
		if (user == null) {
			return;
		}
		List<ClassroomUser> data = this.getData();
		if( data==null || data.size()==0)
			return;
		Iterator<ClassroomUser> iterator = data.iterator();
		while(iterator.hasNext()){
			ClassroomUser classroomUser = iterator.next();
			if(classroomUser.getId().equals(user.getId())){

				update(classroomUser, user);
			}
		}
	}

	/**
	 * user1 is to be updated
	 * @param user1
	 * @param user2
	 */
	@Override
	protected void update(ClassroomUser user1, ClassroomUser user2) {
		if (user1 == null || user2 == null || user1.getId() != user2.getId()) {
			return;
		}
		if (user2.getPassword() != null) {
			user1.setPassword(user2.getPassword());
		}

		if (user2.getPrivilege() != null) {
			user1.setPrivilege(user2.getPrivilege());
		}

		if (user2.getUserName() != null) {
			user1.setUserName(user2.getUserName());
		}


	}
}

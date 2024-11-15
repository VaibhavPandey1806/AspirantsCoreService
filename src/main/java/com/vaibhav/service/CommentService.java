package com.vaibhav.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaibhav.data.dao.Comments;
import com.vaibhav.data.dao.Question;
import com.vaibhav.repos.CommentRepository;
import com.vaibhav.repos.QuestionRepository;

@Service
public class CommentService {
	
	 @Autowired
	    private UserService userService; 
	 
	    @Autowired
	    private  CommentRepository commentRepository;
	    @Autowired
	    private QuestionRepository questionRepository;

	@SuppressWarnings("removal")
	public Comments add(String id, String text) {
		Comments comment=new Comments();
		comment.setDateTimeSubmitted(LocalDateTime.now());
		comment.setSubmittedBy(userService.getUser().getId());
		comment.setText(text);
		comment.setLikes(new Long(0));
		comment.setDislikes(new Long(0));
		Comments added=commentRepository.save(comment);
		Optional<Question> a= questionRepository.findById(id);
		Question q=a.get();
		if(q.getComments()!=null&& q.getComments().size()>0)
		{
			List<String> ab=q.getComments();
			ab.add(added.getId());
		   q.setComments(ab);
		}
		else
		{
			List<String> ab=new ArrayList<String>();
			ab.add(added.getId());
			q.setComments(ab);
		}
		questionRepository.save(q);
		return added;
		
	}

	public Comments likeComment(String id) {
		Comments a=commentRepository.findById(id).get();
		if(a.getLikedBy()!=null&&a.getLikedBy().contains(userService.getUser().getId()))
		{
			
		}
		else {
			a.setLikes(a.getLikes() + 1);
			List<String> temp;
			if (a.getLikedBy() == null||a.getLikedBy().size()==0) {
			temp = new ArrayList<String>();}
			else
			{
				temp=a.getLikedBy();
			}
			temp.add(userService.getUser().getId());
			a.setLikedBy(temp);

		}
		return commentRepository.save(a);
	}

	public Boolean hasLiked(String id) {
		// TODO Auto-generated method stub
		Comments a=commentRepository.findById(id).get();
		if(a.getLikedBy()!=null&&a.getLikedBy().size()>0&&a.getLikedBy().contains(userService.getUser().getId()))
			return true;
		return false;
	}

	public Comments unlikeComment(String id) {
		Comments a=commentRepository.findById(id).get();
//		if(a.getLikedBy()!=null&&a.getLikedBy().contains(userService.getUser().getId()))
//		{
//			
//		}
//		else {
			a.setLikes(a.getLikes() -1);
			List<String> temp;
			if (a.getLikedBy() == null||a.getLikedBy().size()==0) {
			temp = new ArrayList<String>();}
			else
			{
				temp=a.getLikedBy();
			}
			temp.remove(userService.getUser().getId());
			a.setLikedBy(temp);

//		}
		return commentRepository.save(a);
	
	}

	public Comments addReply(String commentId, String text) {
		Comments addReply=commentRepository.findById(commentId).get();
		Comments reply=new Comments();
		reply.setText(text);
		reply.setDateTimeSubmitted(LocalDateTime.now());
		reply.setDislikes(Long.valueOf(0));
		reply.setLikes(Long.valueOf(0));
		reply.setSubmittedBy(userService.getUser().getId());
		Comments added=commentRepository.save(reply);
		List<String> temp;
		if(addReply.getReplies()==null||addReply.getReplies().size()==0)
		{
			temp=new ArrayList<>();
			temp.add(added.getId());
		}
		else 
		{
			temp=addReply.getReplies();
			temp.add(added.getId());
		}
		addReply.setReplies(temp);
		return commentRepository.save(addReply);
	}

}

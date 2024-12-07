package com.vaibhav.data.controler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.vaibhav.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.vaibhav.data.dao.Section;
import com.vaibhav.data.dao.Source;
import com.vaibhav.data.dao.Comments;
import com.vaibhav.data.dao.Question;
import com.vaibhav.data.dao.Responses;
import com.vaibhav.data.dao.Section;
import com.vaibhav.data.dao.Topic;
import com.vaibhav.data.dao.User;
import com.vaibhav.data.dao.UserResponses;
import com.vaibhav.data.dto.UserResponseDto;
import com.vaibhav.repos.CommentRepository;
import com.vaibhav.repos.QuestionRepository;
import com.vaibhav.repos.SectionRepository;
import com.vaibhav.repos.SourceRepository;
import com.vaibhav.repos.TopicRepository;
import com.vaibhav.repos.UserRepository;

@RestController
//@CrossOrigin(origins = "https://aspirantsclub.netlify.app")
@RequestMapping("/api")
public class dataController {
	
	 private final RestTemplate restTemplate;

	    public dataController(RestTemplateBuilder restTemplateBuilder) {
	        this.restTemplate = restTemplateBuilder.build();
	    }
	

	    
	    @Autowired
	    private ExcelToMongoService excelToMongoService;
	    
    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private  UserRepository userRepository;
    
    @Autowired
    private SectionService sectionService; 
    
    @Autowired
    private ResponseService responseService; 
    
    @Autowired
    private SourceRepository sourceRepository;
    
    @Autowired
    private QuestionService questionService; 
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private TopicRepository topicRepository; 
    
    @Autowired
    private  CommentRepository commentRepository;
    
    @Autowired
    private CommentService commentService; 
    
    @Value("${ai.url}")
     String AiUrl;
	
  
   @GetMapping("/questions")
   public ResponseEntity<List<Question>> getAllQuestions() {
	   Question a = new Question();
	   questionRepository.save(a);	   
       return ResponseEntity.ok(questionRepository.findAll());
   }

//   @PostMapping("/addQuestion")
//   public Question addQuestion(@RequestBody Question question) {
//       // Variables to hold section, topic, and source entities
//      return questionService.addQuestion(question).getBody();
//   }
@GetMapping("/addQuestion")
public ResponseEntity<Question> addQuestion(
        @RequestParam(required = false) String sectionId,
        @RequestParam (required = false) String section,
        @RequestParam(required = false) String topicId,
        @RequestParam (required = false) String topic,
        @RequestParam(required = false) String sourceId,
        @RequestParam (required = false) String source,
        @RequestParam String questionText,
        @RequestParam String optionA,
        @RequestParam String optionB,
        @RequestParam String optionC,
        @RequestParam String optionD,
        @RequestParam String correctAnswer) {

    // Create a Question object from the parameters
    Question question = new Question();
    question.setSectionId(sectionId);
    question.setSection(section);
    question.setTopicId(topicId);
    question.setTopic(topic);
    question.setSourceId(sourceId);
    question.setSource(source);
    question.setQuestionText(questionText);
    question.setOptionA(optionA);
    question.setOptionB(optionB);
    question.setOptionC(optionC);
    question.setOptionD(optionD);
    question.setCorrectAnswer(correctAnswer);
    // Save the question using the service layer
    return questionService.addQuestion(question);
}






//   @GetMapping("/questionsbyCategory")
//   public List<Question> getQuestionsByCategory(@RequestParam String category) {
//       return questionRepository.findByCategory(category); // Make sure this method is implemented in your repository
//   }
//   @GetMapping("/categories")
//   public List<String> getAllCategories() {
//       List<Question> questions = questionRepository.findDistinctCategories();
//       // Extract distinct categories from the list of questions
//       return questions.stream()
//               .map(Question::getCategory)
//               .filter(category -> category != null) // Optional, since the query already filters out nulls
//               .distinct()
//               .collect(Collectors.toList());
//   }
   
   @GetMapping("/categoryDetail")
   public ResponseEntity<Section> getCategoryDetail(@RequestParam String categoryId) {
       Optional<Section> categories = sectionRepository.findById(categoryId); // Retrieve all categories
       return ResponseEntity.ok(categories.get());
   }
   @GetMapping("/categories")
   public ResponseEntity<List<Section>> getAllCategories() {
       List<Section> categories = sectionRepository.findAll(); // Retrieve all categories
       return ResponseEntity.ok(categories);
   }
   @GetMapping("/getquestions")
   public List<Question> getQuestionsByCategory1(@RequestParam String category) {
       return questionRepository.findBySectionId(category);
   }
   @GetMapping("/getQuestionCategoryBased")
   public List<Question> getQuestionsByCategory1(@RequestParam List<String> category) {
       List<Question> result=new ArrayList<>();
       for(String a:category)
       {
    	   result.addAll(questionRepository.findBySectionId(a));
    	   }
	   return result;
   }
   @GetMapping("/topics")
   public ResponseEntity<List<Topic>> getAllToics() {
       List<Topic> topics = topicRepository.findAll(); // Retrieve all categories
       return ResponseEntity.ok(topics);
   }
   
   @GetMapping("/sources")
   public ResponseEntity<List<Source>> getAllSources() {
       List<Source> source = sourceRepository.findAll(); // Retrieve all categories
       return ResponseEntity.ok(source);
   }
   @GetMapping("/getTopicsByCategory")
   public List<Topic> getTopicsByCategory(@RequestParam String categoryId) {
       List<Topic> result=new ArrayList<>();
  
    	   result.addAll(topicRepository.findBySectionId(categoryId));
    	   
	   return result;
   }
   @GetMapping("/getQuestionsByCategoryAndTopic")
   public List<Question> getQuestionsByCategoryAndTopic(
           @RequestParam List<String> category, 
           @RequestParam List<String> topic) {
       return questionService.getQuestionsByCategoryAndTopic(category, topic);
   }
   
   @GetMapping("/getQuestionsByFilters")
   public List<Question> getQuestionsByFilters(
           @RequestParam(required = false) List<String> category, 
           @RequestParam(required = false) List<String> topic,
           @RequestParam(required = false) List<String> source) {
       
       // If category is not provided, return empty list or handle accordingly
       if (category == null || category.isEmpty()) {
           return new ArrayList<>(); // Or you can throw an exception if category is mandatory
       }

       // If both category and topic are provided
       if (topic != null && !topic.isEmpty() && source != null && !source.isEmpty()) {
           return questionService.getQuestionsByFilters(category, topic, source);
       }
       // If only category and topic are provided
       else if (topic != null && !topic.isEmpty()) {
           return questionService.getQuestionsByCategoryAndTopic(category, topic);
       }
       // If only category is provided
       else {
           return getQuestionsByCategory1(category);
       }
   }

   @GetMapping("/getQuestionsbyId")
   public Optional<Question> getQuestionsById(@RequestParam String id) {
       return questionRepository.findById(id);
   }
   
   @GetMapping("/getCommentsbyId")
   public Comments getCommentsbyId(@RequestParam String id) {
       return commentRepository.findById(id).get();
   }
   
   @PostMapping("/addComment")
   public Comments addComment(@RequestParam String questionId,
		   @RequestParam String text ) {
	   return commentService.add(questionId,text);
   }
   
   @PostMapping("/addReply")
   public Comments addReply(@RequestParam String commentId,
		   @RequestParam String text ) {
	   return commentService.addReply(commentId,text);
   }
   
   @PostMapping("/likeComment")
   public Comments likeComment(@RequestParam String id) {
       return commentService.likeComment(id);
   }
   
   @GetMapping("/hasLiked")
   public Boolean hasLiked(@RequestParam String id) {
       return commentService.hasLiked(id);
   }
   
   @PostMapping("/unlikeComment")
   public Comments unlikeComment(@RequestParam String id) {
       return commentService.unlikeComment(id);
   }

    @PostMapping("/dislikeComment")
    public Comments dislikeComment(@RequestParam String id) {
        return commentService.dislikeComment(id);
    }

    @GetMapping("/addResponse")
    public String addResponse(
            @RequestParam String userId,
            @RequestParam int timer,
            @RequestParam String questionId,
            @RequestParam boolean response) {
       UserResponseDto userResponseDto = new UserResponseDto();
       userResponseDto.setUserId(userId);
       userResponseDto.setTimer(timer);
       userResponseDto.setQuestionId(questionId);
       userResponseDto.setResponse(response);
       return responseService.addResponse(userResponseDto);
   }
   
   @GetMapping("/getResponses")
   public UserResponses getResponseforUser()
   {
	  return responseService.getResponseforUser();
   }
   
   
   @GetMapping("/AiResponseQuestionsbyId")
   public String AiResponseQuestionsById(@RequestParam String id) {
       Optional<Question> op = questionRepository.findById(id);

       if (!op.isPresent()) {
           return "Question not found for ID: " + id;
       }

       Question question = op.get();
       
       // Construct the prompt
       StringBuilder prompt = new StringBuilder("Section: " + question.getSection() + ", ");
       prompt.append("Topic: " + question.getTopic() + ", ");
       prompt.append("Question: " + question.getQuestionText() + " Options: ");
       
       // Add the options A, B, C, D to the prompt
       prompt.append("A. ").append(question.getOptionA()).append(", ");
       prompt.append("B. ").append(question.getOptionB()).append(", ");
       prompt.append("C. ").append(question.getOptionC()).append(", ");
       prompt.append("D. ").append(question.getOptionD()).append(". ");
       
       // Add the instruction for plain text response
       prompt.append("Give only result and explanation in response in plain text without any post formatting required.");

       // Build the URL with the encoded prompt
       String url = UriComponentsBuilder.fromHttpUrl(AiUrl)
               .queryParam("message", prompt.toString())
               .queryParam("Authorization", "Basic dmFpYmhhdkBwcHA3ODpwYXNzd29yZEB2YWliaGF2MTgwNg==")
               .toUriString();

       // Make the API call
       return restTemplate.getForObject(url, String.class);
   }

   
   @PostMapping("/import-excel")
   public String importExcel(@RequestParam("file") MultipartFile file) {
       try {
           excelToMongoService.importExcelData(file);
           return "Data Imported Successfully!";
       } catch (IOException e) {
           e.printStackTrace();
           return "Failed to import data: " + e.getMessage();
       }
   }
   @GetMapping("/getUserDetails")
 	public User getUser(@RequestParam String id) {
// 		HashMap<String,String> user=new HashMap<String,String>();
 		User user=new User();
 		user=userRepository.findById(id).get();
// 		OAuth2User userDetails=(OAuth2User) authentication.getPrincipal();
// 		user.put("name",userDetails.getAttributes().get("name").toString());
 		return user;
    }


    @GetMapping("/addUserfromWeb")
    public User addUserFromWeb(
            @RequestParam String name,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String mobile,
            @RequestParam String email) {
       User user=new User();
       user.setName(name);
       user.setUsername(username);
       user.setPassword(password);
       user.setMobile(mobile);
       user.setEmailId(email);
       userRepository.save(user);
       return user;
    }

   @GetMapping("/userDetails")
   public User getUser() {


       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      UserDetails userDetails=(UserDetails) authentication.getPrincipal();
      			String user= userDetails.getUsername();

			return userRepository.findByUsername(user);
			

		
   }

   @GetMapping("/isUser")
    public HashMap<String,Boolean> isUser() {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       OAuth2User userDetails=(OAuth2User) authentication.getPrincipal();
       List<User> user=userRepository.findByEmailId((userDetails.getAttributes().get("email").toString()));
       HashMap<String,Boolean> map=new HashMap<>();
       if(user==null||user.isEmpty())
       {
    	  map.put("isUser",false);
       }
       else {
           map.put("isUser",true);
       }

    	   return map;


   }

   @GetMapping("getEmail")
    public String getEmail() {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       OAuth2User userDetails=(OAuth2User) authentication.getPrincipal();
       String user= userDetails.getAttributes().get("email").toString();
       return user;
   }
   
   
}

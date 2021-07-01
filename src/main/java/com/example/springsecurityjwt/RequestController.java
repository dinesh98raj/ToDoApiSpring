package com.example.springsecurityjwt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.springsecurityjwt.models.AuthenticationRequest;
import com.example.springsecurityjwt.models.AuthenticationResponse;
import com.example.springsecurityjwt.models.ListItem;
import com.example.springsecurityjwt.models.MyListItem;
import com.example.springsecurityjwt.models.User;
import com.example.springsecurityjwt.repositories.ListRepository;
import com.example.springsecurityjwt.repositories.UserRepository;
import com.example.springsecurityjwt.services.MyUserDetailsService;
import com.example.springsecurityjwt.util.JwtUtil;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class RequestController {

	@Autowired
	private AuthenticationManager authmanager;
	
	@Autowired
	private MyUserDetailsService uds;
	
	@Autowired
	private JwtUtil jwt;
	
	@Autowired
	private ListRepository listrepo;
	
	@Autowired
	private UserRepository userrepo;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authreq) throws Exception{
		authmanager.authenticate(new UsernamePasswordAuthenticationToken(authreq.getUsername(), authreq.getPassword()));
		final UserDetails usrdetails = uds.loadUserByUsername(authreq.getUsername());
		final String token = jwt.generateToken(usrdetails); 
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello World";
	}
	
	@GetMapping("/gettasks")
	public ResponseEntity<?> getAllTask(){
		//to get the name of the current user
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = ((UserDetails)principal).getUsername();
		//to find the id of the user using name 
		Optional<User> opuser = userrepo.findByUserName(name);
		User user = opuser.get();
		Integer userid = user.getUserid();
		List<ListItem> lstitm = listrepo.findAllByUserid(userid);
		List<MyListItem> rtnlist = lstitm.stream().map(lsttemp -> {
			MyListItem templist = new MyListItem(lsttemp);
			return templist;
		}).collect(Collectors.toList());
		return ResponseEntity.ok(rtnlist);
	}
	
	@GetMapping("/getparticulartask/{taskid}")
	public ResponseEntity<?> getParticularTask(@PathVariable Integer taskid) {
		//to get the name of the current user
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = ((UserDetails)principal).getUsername();
		//to find the id of the user using name 
		Optional<User> opuser = userrepo.findByUserName(name);
		User user = opuser.get();
		Integer userid = user.getUserid();
		ListItem lstitm = listrepo.findByTaskNoAndUserid(taskid, userid);
		MyListItem rtnlist = new MyListItem(lstitm);
		return ResponseEntity.ok(rtnlist);
	}
	
	@PostMapping("/addtask")
	public ResponseEntity<?> createListItem(@RequestBody ListItem lstitm) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = ((UserDetails)principal).getUsername();
		//to find the id of the user using name 
		Optional<User> opuser = userrepo.findByUserName(name);
		User user = opuser.get();
		Integer userid = user.getUserid();
		lstitm.setUserid(userid);
		listrepo.save(lstitm);
		MyListItem rtnlist = new MyListItem(lstitm);
		return ResponseEntity.ok(rtnlist);
	}
	
	@PutMapping("/editTask/{taskid}")
    public ResponseEntity<?> updateParticularTask(@PathVariable Integer taskid,
    @RequestBody MyListItem lst) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = ((UserDetails)principal).getUsername();
		//to find the id of the user using name 
		Optional<User> opuser = userrepo.findByUserName(name);
		User user = opuser.get();
		Integer userid = user.getUserid();
		ListItem lstitm = listrepo.findByTaskNoAndUserid(taskid, userid);
  
        lstitm.setListItem(lst.getListItem());
        ListItem updatedlst = listrepo.save(lstitm);
        MyListItem updlst = new MyListItem(updatedlst);
        return ResponseEntity.ok(updlst);
   }
	
	@PutMapping("/updateStatus/{taskid}")
    public ResponseEntity<?> updateParticularTask(@PathVariable Integer taskid) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = ((UserDetails)principal).getUsername();
		//to find the id of the user using name 
		Optional<User> opuser = userrepo.findByUserName(name);
		User user = opuser.get();
		Integer userid = user.getUserid();
		ListItem lstitm = listrepo.findByTaskNoAndUserid(taskid, userid);
  
        lstitm.setStatus(Boolean.TRUE);
        ListItem updatedlst = listrepo.save(lstitm);
        MyListItem updlst = new MyListItem(updatedlst);
        return ResponseEntity.ok(updlst);
   }
	
	@DeleteMapping("/deletetask/{taskid}")
	public ResponseEntity<Map<String, Boolean>> deleteParticularTask(@PathVariable Integer taskid){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = ((UserDetails)principal).getUsername();
		//to find the id of the user using name 
		Optional<User> opuser = userrepo.findByUserName(name);
		User user = opuser.get();
		Integer userid = user.getUserid();
		
		ListItem lstitm = listrepo.findByTaskNoAndUserid(taskid, userid);
		listrepo.delete(lstitm);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
		
}

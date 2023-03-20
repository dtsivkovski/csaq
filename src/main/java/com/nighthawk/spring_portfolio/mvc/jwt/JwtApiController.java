package com.nighthawk.spring_portfolio.mvc.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.nighthawk.spring_portfolio.mvc.person.Person;
import com.nighthawk.spring_portfolio.mvc.person.PersonDetailsService;

@RestController
@CrossOrigin
public class JwtApiController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private PersonDetailsService personDetailsService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody Person authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		final UserDetails userDetails = personDetailsService
				.loadUserByUsername(authenticationRequest.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails);
		final ResponseCookie tokenCookie = ResponseCookie.from("jwt", token)
			.httpOnly(true)
			.secure(true)
			.path("/")
			.maxAge(3600)
			.sameSite("None; Secure")
			// .domain("example.com") // Set to backend domain
			.build();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, tokenCookie.toString()).build();
	}

	@PostMapping("/register")
	public ResponseEntity<?> createPersonAndAuthToken(@RequestBody String np) throws Exception {
		System.out.println(np);
		Person a;
		try {
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(np);
			a = new Person(obj.get("email").toString(), obj.get("password").toString(), obj.get("name").toString(), obj.get("gender").toString());
			if (personDetailsService.getByEmail(a.getEmail()) != null) {
				throw new Exception("USER_ALREADY_EXISTS");
			}
			else {
				personDetailsService.save(a);
				return createAuthenticationToken(a);
			}
		} catch (Exception e) {
			throw new Exception("USER_ALREADY_EXISTS", e);
		}		// auth and get user details
	}

	@GetMapping("/logoutJWT")
	public ResponseEntity<?> logout() {
		final ResponseCookie tokenCookie = ResponseCookie.from("jwt", "")
			.httpOnly(true)
			.secure(true)
			.path("/")
			.maxAge(0)
			.sameSite("none")
			.build();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, tokenCookie.toString()).build();
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
}
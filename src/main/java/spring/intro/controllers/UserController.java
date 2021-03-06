package spring.intro.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.intro.dto.UserResponseDto;
import spring.intro.model.User;
import spring.intro.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/inject")
    public String injectUsers() {
        userService.add(new User("Ross", 24));
        userService.add(new User("Sofia", 22));
        userService.add(new User("Jacob", 20));
        userService.add(new User("Emily", 22));

        return "Injection Successful";
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        User user = userService.get(userId);
        return getUserResponseDto(user);
    }

    @GetMapping("/")
    public List<UserResponseDto> getAll() {
        return userService.listUsers()
                .stream()
                .map((this::getUserResponseDto))
                .collect(Collectors.toList());
    }

    private UserResponseDto getUserResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setName(user.getName());
        dto.setAge(user.getAge());

        return dto;
    }
}

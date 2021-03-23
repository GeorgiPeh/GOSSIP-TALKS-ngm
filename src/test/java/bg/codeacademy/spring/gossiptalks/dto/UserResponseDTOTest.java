package bg.codeacademy.spring.gossiptalks.dto;

import bg.codeacademy.spring.gossiptalks.service.UserService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.*;

public class UserResponseDTOTest
{
  @Mock
  private UserService userServiceMock;

  @BeforeTest

  public void setup()
  {
    MockitoAnnotations.initMocks(this);
  }

  @DataProvider(name = "data-provider")

  public Object[][] dataProviderMethod()
  {

    UserResponseDTO userDto = new UserResponseDTO();

    userDto.username = null;

    return new Object[][]{
        {userDto}
    };
  }


}
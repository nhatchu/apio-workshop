# Steps for the workshop
---
## JAX-RS annotations

1 - Create Application

```java
@Component(
	property = {
		"osgi.jaxrs.application.base=/rest",
		"osgi.jaxrs.name=recipes-application",
		"auth.verifier.auth.verifier.BasicAuthHeaderAuthVerifier.urls.includes=*"
	},
	service = Application.class
)
public class RecipesApplication extends Application {

	@Override
	public Set<Object> getSingletons() {
		return Collections.singleton(this);
	}

	@GET
	@Produces(TEXT_PLAIN)
	@Path("hello")
	public String helloWorld() {
		return "Hello World";
	}

}
```

---

2 - Create Organization resource

```java
@Component(
    immediate = true,
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=recipes-application)",
		"osgi.jaxrs.resource=true"
	},
	service = Object.class
)
@Path("organization")
public class OrganizationResource {

	@GET
	@Path("{id}")
	public String retrieve(@PathParam("id") long id) throws PortalException {
		Organization organization = _organizationService.getOrganization(id);

		return organization.getName();
	}

	@GET
	public String retrieve(@Context User user) throws PortalException {
		List<Organization> organizations =
			_organizationService.getUserOrganizations(user.getUserId());

		return organizations.stream()
			.map(organization -> organization.getGroupId() + " : " + organization.getName())
			.collect(Collectors.joining(", "));
	}
    
    @GET
	@Path("{id}/recipes")
	public String retrieveRecipes(@PathParam("id") long groupId) {
		return _recipeService.getRecipesByGroupId(groupId, -1, -1)
			.stream()
			.map(Recipe::getName)
			.collect(Collectors.joining("\n"));
	}
    
    @Reference
	private RecipeService _recipeService;

	@Reference
	private OrganizationService _organizationService;

}
```

---

 3 - Create recipes resource

```java
@Component(
	immediate = true,
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=recipes-application)",
		"osgi.jaxrs.resource=true"
	},
	service = Object.class
)
@Path("recipe")
public class RecipeResource {

	@GET
	@Path("{id}")
	public String retrieve(@PathParam("id") long id) throws PortalException {
		return _recipeService.getRecipe(id).getName();
	}

	@Reference
	private RecipeService _recipeService;

}
```

---

4 - Create Person resource

```java
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=recipes-application)",
		"osgi.jaxrs.resource=true"
	},
	service = Object.class,
	immediate = true
)
@Path("user")
public class UserResource {

	@GET
	@Path("{id}")
	public String retrieve(@PathParam("id") long id) throws PortalException {
		User user = _userService.getUserById(id);
        
        return user.getFullName();
	}

	@Reference
	private UserService _userService;
}
```
## Jackson models

---

 1 - Organization

```java
@XmlRootElement
public class OrganizationDTO {

	private long id;
	private String name;
	private String logoURL;
	private AddressDTO address;
	private Long chefId;

	public OrganizationDTO() {
	}

	public OrganizationDTO(Organization organization) {
		id = organization.getGroupId();
		name = organization.getName();
		logoURL = Utils.getLogoURL(organization);
		address = new AddressDTO(organization.getAddress());
		chefId = Utils.getChefId(organization);
	}

	@XmlElement
	public Long getChefId() {
		return chefId;
	}

	@XmlElement
	public long getId() {
		return id;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	@XmlElement
	public String getLogoURL() {
		return logoURL;
	}

	@XmlElement
	public AddressDTO getAddress() {
		return address;
	}
}


```

---

 2 - AddressDTO

```java
@XmlRootElement
public class AddressDTO {

	private String street;
	private String zipCode;
	private String regionName;
	private String countryName;

	public AddressDTO() {

	}

	public AddressDTO(Address address) {
		countryName = address.getCountry().getName();
		zipCode = address.getZip();
		regionName = address.getRegion().getName();
		street = address.getStreet1();
	}

	@XmlElement
	public String getStreet() {
		return street;
	}

	@XmlElement
	public String getZipCode() {
		return zipCode;
	}

	@XmlElement
	public String getRegionName() {
		return regionName;
	}

	@XmlElement
	public String getCountryName() {
		return countryName;
	}
}

```

---

 3 - Recipe

```java
@XmlRootElement
public class RecipeDTO {

	private Date createDate;
	private Date modifiedDate;
	private Date publishedDate;
	private String region;
	private List<String> ingredients;
	private List<String> steps;
	private long userId;
	private long id;
	private String videoURL;
	private String cookTime;
	private String category;
	private List<String> recipeAssetTags;
	private String imageURL;

	public RecipeDTO() {

	}

	public RecipeDTO(Recipe recipe) {
		id = recipe.getRecipeId();
		createDate = recipe.getCreateDate();
		modifiedDate = recipe.getModifiedDate();
		publishedDate = recipe.getPublishedDate();
		region = recipe.getRegion();
		ingredients = recipe.getIngredients();
		steps = recipe.getSteps();
		userId = recipe.getUserId();
		videoURL = recipe.getVideoURL();
		cookTime = Utils.getCookTime(recipe);
		category = Utils.getCategory(recipe);
		recipeAssetTags = Utils.getRecipeAssetTags(recipe);
		imageURL = Utils.getImageURL(recipe);
	}

	@XmlElement
	public Date getCreateDate() {
		return createDate;
	}

	@XmlElement
	public Date getModifiedDate() {
		return modifiedDate;
	}

	@XmlElement
	public Date getPublishedDate() {
		return publishedDate;
	}

	@XmlElement
	public String getRegion() {
		return region;
	}

	@XmlElement
	public List<String> getIngredients() {
		return ingredients;
	}

	@XmlElement
	public List<String> getSteps() {
		return steps;
	}

	@XmlElement
	public long getUserId() {
		return userId;
	}

	@XmlElement
	public long getId() {
		return id;
	}

	@XmlElement
	public String getVideoURL() {
		return videoURL;
	}

	@XmlElement
	public String getCookTime() {
		return cookTime;
	}

	@XmlElement
	public String getCategory() {
		return category;
	}

	@XmlElement
	public List<String> getRecipeAssetTags() {
		return recipeAssetTags;
	}

	@XmlElement
	public String getImageURL() {
		return imageURL;
	}
}
```

---

 4 - User

```java
@XmlRootElement
public class UserDTO {

	private long id;
	private String screenName;
	private String email;
	private String firstName;
	private String lastName;
	private String jobTitle;
	private String fullName;
	private String portraitURL;

	public UserDTO() {}

	public UserDTO(User user) {
		id = user.getUserId();
		screenName = user.getScreenName();
		email = user.getEmailAddress();
		firstName = user.getFirstName();
		lastName = user.getLastName();
		jobTitle = user.getJobTitle();
		fullName = user.getFullName();
		portraitURL = Utils.getPortraitURL(user);
	}

	@XmlElement
	public String getPortraitURL() {
		return portraitURL;
	}

	@XmlElement
	public long getId() {
		return id;
	}

	@XmlElement
	public String getScreenName() {
		return screenName;
	}

	@XmlElement
	public String getEmail() {
		return email;
	}

	@XmlElement
	public String getFirstName() {
		return firstName;
	}

	@XmlElement
	public String getLastName() {
		return lastName;
	}

	@XmlElement
	public String getJobTitle() {
		return jobTitle;
	}

	@XmlElement
	public String getFullName() {
		return fullName;
	}
}
```

---

## Returning JSON

 1 - Organization resource

```java
@Component(
	immediate = true,
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=recipes-application)",
		"osgi.jaxrs.resource=true"
	},
	service = Object.class
)
@Path("organization")
public class OrganizationResource {

	@GET
	@Path("{id}")
	public OrganizationDTO retrieve(@PathParam("id") long id) throws PortalException {
		Organization organization = _organizationService.getOrganization(id);

		return new OrganizationDTO(organization);
	}

	@GET
	public List<OrganizationDTO> retrieve(@Context User user) throws PortalException {
		List<Organization> organizations =
			_organizationService.getUserOrganizations(user.getUserId());

		return organizations.stream()
			.map(OrganizationDTO::new)
			.collect(Collectors.toList());
	}

	@GET
	@Path("{id}/recipes")
	public List<RecipeDTO> retrieveRecipes(@PathParam("id") long groupId) {
		return _recipeService.getRecipesByGroupId(groupId, -1, -1)
			.stream()
			.map(RecipeDTO::new)
			.collect(Collectors.toList());
	}

	@Reference
	private RecipeService _recipeService;
	@Reference
	private OrganizationService _organizationService;

}
```

---

 2 - Recipe resource 

```java
@Component(
	immediate = true,
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=recipes-application)",
		"osgi.jaxrs.resource=true"
	},
	service = Object.class
)
@Path("recipe")
public class RecipeResource {

	@GET
	@Path("{id}")
	public RecipeDTO retrieveRecipe(@PathParam("id") long id) throws PortalException {
		return new RecipeDTO(_recipeService.getRecipe(id));
	}

	@Reference
	private RecipeService _recipeService;

}
```

---

 3 - User resource 

```java
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=recipes-application)",
		"osgi.jaxrs.resource=true"
	},
	service = Object.class,
	immediate = true
)
@Path("user")
public class UserResource {

	@GET
	@Path("{id}")
	public UserDTO retrieve(@PathParam("id") long id) throws PortalException {
		return new UserDTO(_userService.getUserById(id));
	}


	@Reference
	private UserService _userService;
}
```

## Returning APIO types

Types:
---
```java
@Type("Person")
public interface PersonType extends Identifier<Long> {

	@Field("portraitUrl")
	public String getPortraitURL();

	@Id
	public long getId();

	@Field("screenName")
	public String getScreenName();

	@Field("email")
	public String getEmail();

	@Field("firstName")
	public String getFirstName();

	@Field("lastName")
	public String getLastName();

	@Field("jobTitle")
	public String getJobTitle();

	@Field("fullName")
	public String getFullName();
}
```

```java
@Type("PostalAddress")
public interface PostalAddressType {

	@Field("street")
	public String getStreet();

	@Field("zipCode")
	public String getZipCode();

	@Field("regionName")
	public String getRegionName();

	@Field("countryName")
	public String getCountryName();
}
```

```java
@Type("Recipe")
public interface RecipeType extends Identifier<Long> {

	@Field("createDate")
	public Date getCreateDate();

	@Field("modifiedDate")
	public Date getModifiedDate();

	@Field("publishedDate")
	public Date getPublishedDate();

	@Field("region")
	public String getRegion();

	@Field("ingredients")
	public List<String> getIngredients();

	@Field("steps")
	public List<String> getSteps();

	@Field("userId")
	public long getUserId();

	@Id
	public long getId();

	@Field("videoUrl")
	public String getVideoURL();

	@Field("cookTime")
	public String getCookTime();

	@Field("category")
	public String getCategory();

	@Field("recipeAssetTags")
	public List<String> getRecipeAssetTags();

	@Field("imageUrl")
	public String getImageURL();
}
```

```java
@Type("Restaurant")
public interface RestaurantType extends Identifier<Long> {

	@Field("chefId")
	@LinkedModel(PersonType.class)
	public Long getChefId();

	@Id
	public long getId();

	@Field("name")
	public String getName();

	@Field("logoUrl")
	public String getLogoURL();

	@Field("address")
	public PostalAddressType getAddress();

	@Field("recipes")
	@RelatedCollection(RecipeType.class)
	public default long getRecipes() {
		return getId();
	}
}
```

DTOs
---

```java
public class AddressDTO implements PostalAddressType {

	private String street;
	private String zipCode;
	private String regionName;
	private String countryName;

	public AddressDTO(Address address) {
		countryName = address.getCountry().getName();
		zipCode = address.getZip();
		regionName = address.getRegion().getName();
		street = address.getStreet1();
	}

	@Override
	public String getStreet() {
		return street;
	}

	@Override
	public String getZipCode() {
		return zipCode;
	}

	@Override
	public String getRegionName() {
		return regionName;
	}

	@Override
	public String getCountryName() {
		return countryName;
	}
}
```

```java
public class OrganizationDTO implements RestaurantType {

	private long id;
	private String name;
	private String logoURL;
	private AddressDTO address;
	private Long chefId;

	public OrganizationDTO(Organization organization) {
		id = organization.getOrganizationId();
		name = organization.getName();
		logoURL = Utils.getLogoURL(organization);
		address = new AddressDTO(organization.getAddress());
		chefId = Utils.getChefId(organization);
	}

	@Override
	public Long getChefId() {
		return chefId;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getLogoURL() {
		return logoURL;
	}

	@Override
	public PostalAddressType getAddress() {
		return address;
	}
}
```

```java
public class RecipeDTO implements RecipeType {

	private Date createDate;
	private Date modifiedDate;
	private Date publishedDate;
	private String region;
	private List<String> ingredients;
	private List<String> steps;
	private long userId;
	private long id;
	private String videoURL;
	private String cookTime;
	private String category;
	private List<String> recipeAssetTags;
	private String imageURL;

	public RecipeDTO(Recipe recipe) {
		id = recipe.getRecipeId();
		createDate = recipe.getCreateDate();
		modifiedDate = recipe.getModifiedDate();
		publishedDate = recipe.getPublishedDate();
		region = recipe.getRegion();
		ingredients = recipe.getIngredients();
		steps = recipe.getSteps();
		userId = recipe.getUserId();
		videoURL = recipe.getVideoURL();
		cookTime = Utils.getCookTime(recipe);
		category = Utils.getCategory(recipe);
		recipeAssetTags = Utils.getRecipeAssetTags(recipe);
		imageURL = Utils.getImageURL(recipe);
	}

	@Override
	public Date getCreateDate() {
		return createDate;
	}

	@Override
	public Date getModifiedDate() {
		return modifiedDate;
	}

	@Override
	public Date getPublishedDate() {
		return publishedDate;
	}

	@Override
	public String getRegion() {
		return region;
	}

	@Override
	public List<String> getIngredients() {
		return ingredients;
	}

	@Override
	public List<String> getSteps() {
		return steps;
	}

	@Override
	public long getUserId() {
		return userId;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getVideoURL() {
		return videoURL;
	}

	@Override
	public String getCookTime() {
		return cookTime;
	}

	@Override
	public String getCategory() {
		return category;
	}

	@Override
	public List<String> getRecipeAssetTags() {
		return recipeAssetTags;
	}

	@Override
	public String getImageURL() {
		return imageURL;
	}
}
```

```java
public class UserDTO implements PersonType {

	private long id;
	private String screenName;
	private String email;
	private String firstName;
	private String lastName;
	private String jobTitle;
	private String fullName;
	private String portraitURL;

	public UserDTO(User user) {
		id = user.getUserId();
		screenName = user.getScreenName();
		email = user.getEmailAddress();
		firstName = user.getFirstName();
		lastName = user.getLastName();
		jobTitle = user.getJobTitle();
		fullName = user.getFullName();
		portraitURL = Utils.getPortraitURL(user);
	}

	@Override
	public String getPortraitURL() {
		return portraitURL;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getScreenName() {
		return screenName;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public String getJobTitle() {
		return jobTitle;
	}

	@Override
	public String getFullName() {
		return fullName;
	}
}
```

---

## Transform JAX-RS resources

Action routers:
---

```java
@Component
public class PersonActionRouter implements ActionRouter<PersonType> {

	@Retrieve
	public PersonType retrieve(@Id long id) throws PortalException {
		return new UserDTO(_userService.getUserById(id));
	}

	@Reference
	private UserService _userService;
}
```

```java
@Component
public class RecipeActionRouter implements ActionRouter<RecipeType> {

	@Retrieve
	public List<RecipeType> retrieveRecipes(
		@ParentId(RestaurantType.class) long groupId, User user) throws PortalException {

		Group organizationGroup = _groupLocalService.getOrganizationGroup(user.getCompanyId(), groupId);

		return _recipeService.getRecipesByGroupId(organizationGroup.getGroupId(), -1, -1)
			.stream()
			.map(RecipeDTO::new)
			.collect(Collectors.toList());
	}

	@Retrieve
	public RecipeType retrieveRecipe(@Id long id) throws PortalException {
		return new RecipeDTO(_recipeService.getRecipe(id));
	}

	@Reference
	private RecipeService _recipeService;

	@Reference
	private GroupLocalService _groupLocalService;

}
```

```java
@Component
public class RestaurantActionRouter implements ActionRouter<RestaurantType> {

	@Retrieve
	public RestaurantType retrieve(@Id long id) throws PortalException {
		Organization organization = _organizationService.getOrganization(id);

		return new OrganizationDTO(organization);
	}

	@Retrieve
	@EntryPoint
	public List<RestaurantType> retrieve(User user) throws PortalException {
		List<Organization> organizations =
			_organizationService.getUserOrganizations(user.getUserId());

		return organizations.stream()
			.map(OrganizationDTO::new)
			.collect(Collectors.toList());
	}



	@Reference
	private OrganizationService _organizationService;

}
```

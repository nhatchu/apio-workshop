<div align="center">
    <a href="https://github.com/liferay/com-liferay-apio-architect" target="blank"><img src="./images/logo.png"/></a>
</div>

# How to develop Liferay Portal APIs with Apio Architect

This project provides a base Liferay project to learn how to use Apio Architect to expose Hypermedia REST APIs of different interconnected Liferay Portal services.

The project modules bring the concept of [recipe](https://github.com/liferay-labs/apio-workshop/blob/master/liferay-recipes/liferay-recipes-service/service.xml#L7) into Liferay Portal.

Our goal in this workshop is to expose Hypermedia REST APIs that strongly "rest" on shared vocabularies, so that they follow [standards](https://schema.org/Recipe) as much as they can.

## Deploy Application Jars

Once you have the Liferay Portal 7.1 GA1 instance ready with the latest Apio Architect version and the demo data creators, you are ready to deploy the Liferay Recipes application into your instance.

Just update the `gradle.properties` `liferay.recipes.deploy.dir` property to your `$LIFERAY_HOME/deploy` folder and execute `gradle deploy` in the root of the repository.

## The data

This project comes with its own set of demo data, so you don't have to create any user, site or mock data. You just need to focus on coding (yay!!).

Specifically, once the packages are deployed, the following data will be created:

- Around [25 organizations](https://github.com/liferay-labs/apio-workshop/blob/master/liferay-recipes/liferay-recipes-demo/src/main/java/com/liferay/recipes/demo/internal/RecipesDemo.java#L346) that represent different restaurants.
- [A chef for each organization](https://github.com/liferay-labs/apio-workshop/blob/master/liferay-recipes/liferay-recipes-demo/src/main/java/com/liferay/recipes/demo/internal/RecipesDemo.java#L183): this person will be the one in charge of the restaurant and will be the only one that can create, update or delete recipes in that restaurant's site.
- [Around 25 kitchen assistants](https://github.com/liferay-labs/apio-workshop/blob/master/liferay-recipes/liferay-recipes-demo/src/main/java/com/liferay/recipes/demo/internal/RecipesDemo.java#L207) that will be assigned to between 1 and 5 restaurants. This assistants will have only permission to see the restaurants they are assigned to, and their recipes.
- [Around 25 recipes per-restaurant](https://github.com/liferay-labs/apio-workshop/blob/master/liferay-recipes/liferay-recipes-demo/src/main/java/com/liferay/recipes/demo/internal/RecipesDemo.java#L329).

## Launch client app

For launching the client app you just have to run `npm install` followed by `npm start` from the `restaurant-app` directory. Then navigate to [http://localhost:4200](http://localhost:4200) on your preferred browser.

## Code time!

The steps for the workshop can be found [here](STEPS.markdown).
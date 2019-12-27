package engine;


/**
 * Jimmy Deng, June 07, 2019
 * IGameLogic interface is implemented by the game itself
 * and it makes sure the game has the required function.
 */
public interface IGameLogic {

    void init(Window window) throws Exception;

    void input(Window window, MouseInput mouseInput);

    void update(float interval, MouseInput mouseInput);

    void render(Window window);

    void cleanup();
}
package ua.artcode.todo.model;

public class Todo {

    private int id;

    private String title;

    private String body;

    private boolean done;

    @java.beans.ConstructorProperties({"id", "title", "body", "done"})
    public Todo(int id, String title, String body, boolean done) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.done = done;
    }

    public Todo() {
    }

    public static TodoBuilder builder() {
        return new TodoBuilder();
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Todo)) return false;
        final Todo other = (Todo) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.id != other.id) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.id;
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Todo;
    }

    public String toString() {
        return "Todo(id=" + this.id + ", title=" + this.title + ", body=" + this.body + ", done=" + this.done + ")";
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getBody() {
        return this.body;
    }

    public boolean isDone() {
        return this.done;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public static class TodoBuilder {
        private int id;
        private String title;
        private String body;
        private boolean done;

        TodoBuilder() {
        }

        public Todo.TodoBuilder id(int id) {
            this.id = id;
            return this;
        }

        public Todo.TodoBuilder title(String title) {
            this.title = title;
            return this;
        }

        public Todo.TodoBuilder body(String body) {
            this.body = body;
            return this;
        }

        public Todo.TodoBuilder done(boolean done) {
            this.done = done;
            return this;
        }

        public Todo build() {
            return new Todo(id, title, body, done);
        }

        public String toString() {
            return "Todo.TodoBuilder(id=" + this.id + ", title=" + this.title + ", body=" + this.body + ", done=" + this.done + ")";
        }
    }
}

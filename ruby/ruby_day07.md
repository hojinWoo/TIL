##  Class

```ruby
class NewClass
    
end
```

`NewClass` has the ability to create new Ruby objects of class `NewClass`

class names start with a capital letter and use CamelCase instead of relying_on_underscores.



```ruby
class Car
  def initialize(make, model) 
    @make = make
    @model = model
  end
end

# instance variable
kitt = Car.new("Pontiac", "Trans Am")
```



### Scope

Scope of a variable is the context in which it's visible to the program

```ruby
class Computer
  ##global variable
  $manufacturer = "Mango Computer, Inc."
  ##class variable
  @@files = {hello: "Hello, world!"}
  
  def initialize(username, password)
    @username = username
    @password = password
  end
  
  def current_user
    @username
  end
  
  def self.display_files
    @@files
  end
end

puts $manufacturer

# Make a new Computer instance:
hal = Computer.new("Dave", 12345)

puts "Current user: #{hal.current_user}"
# @username belongs to the hal instance.

puts "Manufacturer: #{$manufacturer}"
# $manufacturer is global! We can get it directly.

puts "Files: #{Computer.display_files}"
# @@files belongs to the Computer class.
```



## Inheritance

Inheritance is the process by which one class takes on the attributes and methods of another, and it's used to express an *is-a* relationship

```ruby
class ApplicationError
  def display_error
    puts "Error! Error!"
  end
end

class SuperBadError < ApplicationError
end

err = SuperBadError.new
err.display_error
```



### Override

```ruby
class Creature
  def initialize(name)
    @name = name
  end
  
  def fight
    return "Punch to the chops!"
  end
end

# Add your code below!
class Dragon < Creature
  def fight
    return "Breathes fire!"
  end
end

###################################3
class DerivedClass < Base
  def some_method
    super(optional args)
      # Some stuff
    end
  end
end
```



##### Any given Ruby class can have only one superclass

However, Ruby allows this through the use of *mixins*.
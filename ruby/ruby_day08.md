## Class 2

Methods are public by default in Ruby, so if you don't specify `public` or `private`, your methods will be public

```ruby
class Dog
  def initialize(name, breed)
    @name = name
    @breed = breed
  end
  public
  def bark
    puts "Woof!"
  end
end
```



### attr_reader, attr_writer

`attr_reader` to access a variable 

`attr_writer` to change it

`attr_accessor` to make a variable readable *and* writeable in one fell swoop(both  read and write a particular variable)

```ruby
class Person
  def initialize(name, job)
    @name = name
    @job = job
  end
  
  def name
    @name
  end
  
  def job=(new_job)
    @job = new_job
  end
end
```

> Ruby does something like this for us automatically

```ruby
# 축소하화기
class Person
  attr_reader :name
  attr_writer :job
  def initialize(name, job)
    @name = name
    @job = job
  end
end
```

```ruby
class Person
  
  attr_reader :name
#  attr_reader :job
#  attr_writer :job
  attr_accessor :job
  
  def initialize(name, job)
    @name = name
    @job = job
  end
end
```



## Module

As a toolbox that contains a set methods and constants.

You can think of modules as being very much like classes, only modules can't create instances and can't have subclasses. They're just used to store things.

```ruby
module MyLibrary 
  FAVE_BOOK = "Harry Potter"
end
```

```ruby
require 'date'
puts Date.today #2018-08-24
```

```ruby
class Angle
  include Math #includes a certain module can use those module's methods
  attr_accessor :radians
  
  def initialize(radians)
    @radians = radians
  end
  
  def cosine
    cos(@radians)
  end
end

acute = Angle.new(1)
acute.cosine
```

> everything has been pulled in, you can simply write `cos` instead of `Math::cos`



### mixin

When a module is used to mix additional behavior and information into a class



`include` mixes a module's methods in at the instance level (allowing instances of a particular class to use the methods)

```ruby
module Action
  def jump
    @distance = rand(4) + 2
    puts "I jumped forward #{@distance} feet!"
  end
end

class Rabbit
  include Action
  attr_reader :name
  def initialize(name)
    @name = name
  end
end

class Cricket
  include Action
  attr_reader :name
  def initialize(name)
    @name = name
  end
end

peter = Rabbit.new("Peter")
jiminy = Cricket.new("Jiminy")

peter.jump
jiminy.jump
```



### Extend

`extend` keyword mixes a module's methods at the *class* level. This means that *class itself* can use the methods, as opposed to *instances* of the class

```ruby
# ThePresent has a .now method that we'll extend to TheHereAnd

module ThePresent
  def now
    puts "It's #{Time.new.hour > 12 ? Time.new.hour - 12 : Time.new.hour}:#{Time.new.min} #{Time.new.hour > 12 ? 'PM' : 'AM'} (GMT)."
  end
end

class TheHereAnd
  extend ThePresent
end

TheHereAnd.now
```


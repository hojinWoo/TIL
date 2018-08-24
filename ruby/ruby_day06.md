## **Refactoring** 

the process by which we improve a code's structure, appearance, and/or performance without modifying its overall behavior

- omit needless words like `if ~ end` or `unless ~ end` just one line.

- remove unnecessary `return` keyword
  only way to return early is with an explicit `return` statement.




## yields

```ruby
##ex1##
def block_test
  puts "We're in the method!"
  puts "Yielding to the block..."
  yield
  puts "We're back in the method!"
end

block_test { puts ">>> We're in the block!" }

##ex2##
def yield_name(name)
  puts "In the method! Let's yield."
  yield("Kim")
  puts "In between the yields!"
  yield(name)
  puts "Block complete! Back in the method."
end

yield_name("Eric") { |n| puts "My name is #{n}." }

# Now call the method with your name!
yield_name("hojin") { |n| puts "My name is #{n}." }

##ex3##
def double(num)
  yield(num)
end

double(16) {|x| puts x * 2}
```

> Procs are great for keeping your code DRY	
>
> DRY : Don't Repeat Yourself



### Proc Syntax

1. Procs are full-fledged objects, so they have all the powers and abilities of objects. (Blocks do not.)
2. Unlike blocks, procs can be called over and over without rewriting them. This prevents you from having to retype the contents of your block every time you need to execute a particular bit of code.

```ruby
floats = [1.2, 3.45, 0.91, 7.727, 11.42, 482.911]
round_down = Proc.new{|x| x.floor}

ints = floats.collect(&round_down)
print ints #[1, 3, 0, 7, 11, 482]


################################################

phrase = Proc.new{puts "Hello there!"}

def greeter
  yield
end

greeter(&phrase) #Hello there!

################################################

Proc.new{puts "Hello!"}.call #Hello!

################################################
#Symbols & Procs
numbers_array = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

strings_array = numbers_array.map(&:to_s)

################################################
#Ruby Lambda
def lambda_demo(a_lambda)
  puts "I'm the method!"
  a_lambda.call
end

lambda_demo(lambda { puts "I'm the lambda!" })
#I'm the method!
#I'm the lambda!

################################################
#Lambda Syntax
strings = ["leonardo", "donatello", "raphael", "michaelangelo"]
symbolize = lambda { |param| param.to_sym }
symbols = strings.collect(&symbolize)
print symbols #[:leonardo, :donatello, :raphael, :michaelangelo]

################################################
#It returns immediately, without going back
def batman_ironman_proc
  victor = Proc.new { return "Batman will win!" }
  victor.call
  "Iron Man will win!"
end

puts batman_ironman_proc #Batman will win!

#Go back into the method after being called
def batman_ironman_lambda
  victor = lambda { return "Batman will win!" }
  victor.call
  "Iron Man will win!"
end

puts batman_ironman_lambda #Iron Man will win!
```

> First, a lambda checks the number of arguments passed to it, while a proc does not. This means that a lambda will throw an error if you pass it the wrong number of arguments, whereas a proc will ignore unexpected arguments and assign `nil` to any that are missing.
>
> Second, when a lambda returns, it passes control back to the calling method; when a proc returns, it does so immediately, without going back to the calling method.


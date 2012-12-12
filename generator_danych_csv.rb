#! /usr/bin/env ruby

#USAGE: (ruby) generator_danych_csv.rb "path/to/file.csv" columns rows

require 'csv'


class RandomGaussian
  def initialize(mean, stddev, rand_helper = lambda { Kernel.rand })
    @rand_helper = rand_helper
    @mean = mean
    @stddev = stddev
    @valid = false
    @next = 0
  end

  def rand
    if @valid then
      @valid = false
      return @next
    else
      @valid = true
      x, y = self.class.gaussian(@mean, @stddev, @rand_helper)
      @next = y
      return x
    end
  end

  private
  def self.gaussian(mean, stddev, rand)
    theta = 2 * Math::PI * rand.call
    rho = Math.sqrt(-2 * Math.log(1 - rand.call))
    scale = stddev * rho
    x = mean + scale * Math.cos(theta)
    y = mean + scale * Math.sin(theta)
    return x, y
  end
end

$path = ARGV[0]

Mean = 0.7
Deviation = 0.5



CSV.open($path, "wb") do |file|

	columns = ARGV[1].to_i
	rows = ARGV[2].to_i
	
	time = Time.new

	g = RandomGaussian.new(Mean, Deviation)

	rows.times do |c|
		row = [c.to_s]
		row << time.inspect.to_s

		columns.times do
			row << g.rand().to_s
		end

		file << row
	end
end













locals {
  tags = {
    Name = "kurly-clone-promotion"
  }

  ami           = "ami-0e4a9ad2eb120e054"
  instance_type = "t3a.nano"
}

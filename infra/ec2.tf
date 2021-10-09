resource "aws_instance" "ec2" {
  ami           = local.ami
  instance_type = local.instance_type

  tags = local.tags

  root_block_device {
    volume_type = "gp2"
    volume_size = 20
  }
}
